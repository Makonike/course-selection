package com.zxd.www.dpool.datasource;

import com.zxd.www.dpool.connection.IPooledConnection;
import com.zxd.www.dpool.connection.PooledConnection;
import com.zxd.www.dpool.constant.DataSourceConstant;
import com.zxd.www.dpool.exception.PoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * 池化的数据源
 * @author Makonike
 * @date 2021-09-11 11:18
 **/
public class PooledDataSource extends AbstractPooledDataSourceConfig{

    private static volatile PooledDataSource instance = null;

    final static Logger logger = LoggerFactory.getLogger(PooledConnection.class);

    private ScheduledExecutorService executor = null;
    private List<IPooledConnection> pool = null;

    private PooledDataSource() {
        this.init();
    }

    public static synchronized PooledDataSource getInstance(){
        if(instance == null){
            instance = new PooledDataSource();
        }
        return instance;
    }


    /**
     * 归还连接
     * @param connection 连接
     */
    @Override
    public synchronized void returnConnection(IPooledConnection connection) {
        connection.setBusy(false);
        this.notifyAll();
        logger.debug("--- 归还连接: " + connection +  " ---");
    }

    /**
     * 一系列初始化操作
     */
    @Override
    public void init() {
        try {
            // 读取配置文件
            Properties properties = new Properties();
            InputStream in = PooledDataSource.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(in);
            setDriverClass(properties.getProperty(DataSourceConstant.DRIVER_CLASS));
            setJdbcUrl(properties.getProperty(DataSourceConstant.JDBC_URL));
            setUsername(properties.getProperty(DataSourceConstant.USER_NAME));
            setPassword(properties.getProperty(DataSourceConstant.PASSWORD));
            Class.forName(super.getDriverClass());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 初始化数据库连接池
        this.initJdbcPool();
        // 初始化 idle check
        this.initTestOnIdle();
    }

    /**
     * 获取连接
     * @return 连接
     */
    @Override
    public synchronized Connection getConnection(){
        // 获取一个不是繁忙状态的连接
        Optional<IPooledConnection> iPooledConnection = getFreeConnectionFromPool();
        // 如果连接池有空闲连接则直接获取到
        if(iPooledConnection.isPresent()){
            return iPooledConnection.get();
        }
        // 判断是否需要扩容
        if(pool.size() >= maxSize){
            if ((maxWait) <= 0){
                throw new PoolException("Can't get connection from pool!");
            }
            final long startWait = System.currentTimeMillis();
            final long endWait = startWait + maxWait;
            while (System.currentTimeMillis() < endWait){
                Optional<IPooledConnection> optional = getFreeConnectionFromPool();
                if(optional.isPresent()){
                    return optional.get();
                }
                try {
                    this.wait();
//                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new PoolException(e);
                }
                logger.debug("--- 等待连接归还 ---");
            }
            throw new PoolException("Can't get connection from pool! Time out: " + maxWait);
        }
        // 开始扩容操作（1个）
        IPooledConnection newPoolConnection = createPooledConnection();
        // 设置状态为繁忙
        newPoolConnection.setBusy(true);
        // 添加到当前连接池中
        pool.add(newPoolConnection);
        return newPoolConnection;
    }

    /**
     * 获取一个不是繁忙状态的连接
     * @return 不是繁忙状态的连接
     */
    private Optional<IPooledConnection> getFreeConnectionFromPool(){
        for (IPooledConnection iPooledConnection : pool) {
            // 遍历判断是否繁忙，取出并返回一个空闲的连接
            if(!iPooledConnection.isBusy()){
                iPooledConnection.setBusy(true);
                logger.debug("--- 从连接池获取连接 ---");
                return Optional.of(iPooledConnection);
            }
        }
        return Optional.empty();
    }

    /**
     * 初始化连接池
     */
    private void initJdbcPool(){
        final int minSize = super.minSize;
        pool = new ArrayList<>(minSize);
        for (int i = 0; i < minSize; i++) {
            IPooledConnection connection = createPooledConnection();
            pool.add(connection);
        }
    }

    /**
     * 创建池化的连接
     * @return 池化的连接
     */
    private IPooledConnection createPooledConnection(){
        Connection connection = createConnection();
        IPooledConnection iPooledConnection = new PooledConnection();
        // 默认为空闲
        iPooledConnection.setBusy(false);
        iPooledConnection.setDataSource(this);
        // 设置连接
        iPooledConnection.setConnection(connection);
        return iPooledConnection;
    }

    /**
     * 创建新连接
     * @return 新连接
     */
    private Connection createConnection(){
        try {
            return DriverManager.getConnection(super.getJdbcUrl(),super.getUsername(),super.getPassword());
        } catch (SQLException e) {
            throw new PoolException(e);
        }
    }

    /**
     * 初始化空闲的连接的检测
     */
    private void initTestOnIdle(){

        if(!"".equals(validQuery) && validQuery != null){
            // 为空闲的连接检测开一个线程
            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    testOnIdleCheck();
                }
            }, super.autoTestOnIdleSeconds, autoTestOnIdleSeconds, TimeUnit.SECONDS);
            logger.debug("Test on idle config with seconds: " + autoTestOnIdleSeconds);
        }
    }

    /**
     * 检测空闲的连接是否可用
     */
    private void testOnIdleCheck(){
        logger.debug("--- start check test on idle now ---");
        for (IPooledConnection iPooledConnection : pool) {
            if(!iPooledConnection.isBusy()){
                checkValid(iPooledConnection);
            }
        }
        logger.debug("--- finish check test on idle ! ---");
    }

    /**
     * 验证连接是否有效
     */
    private void checkValid(final IPooledConnection pooledConnection){
        if(!"".equals(validQuery) && super.validQuery != null){
            Connection connection = pooledConnection.getConnection();
            try {
                // 若有效则返回true，无效则申请一个新连接来替代
                if(!connection.isValid(super.validTimeOutSeconds)){
                    logger.debug("the connection is inValid, start create one replace it");
                    Connection newConnection = createConnection();
                    pooledConnection.setConnection(newConnection);
                    logger.debug("the connection is inValid, finish create one replace it");
                }
            } catch (SQLException e) {
                throw new PoolException(e);
            }
        } else {
            logger.debug("valid query is empty");
        }
    }

    /**
     * 销毁数据库连接池，关闭所有连接，释放资源
     */
    @Override
    public void destroy(){

        // 关闭检查空闲连接的线程
        executor.shutdown();
        // 关闭所有连接
        for (IPooledConnection iPooledConnection : pool) {
            iPooledConnection.remove();
        }
        if (logger.isInfoEnabled()) {
            logger.info("{dataSource-" + this + "} closing ...");
        }
        // 删除注册驱动
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver(super.getJdbcUrl()));
        } catch (SQLException e) {
            throw new PoolException(e);
        }

    }

}
