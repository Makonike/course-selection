package com.zxd.www.dpool.datasource;


import com.zxd.www.dpool.config.ILifeCycle;
import com.zxd.www.dpool.config.IPooledDataSourceConfig;
import com.zxd.www.dpool.constant.DataSourceConstant;

/**
 * @author Makonike
 * @date 2021-09-11 10:52
 **/
public abstract class AbstractPooledDataSourceConfig extends AbstractDataSourceConfig
                                      implements IPooledDataSourceConfig, ILifeCycle {
    /**
     * 最小连接数
     */
    protected int minSize = DataSourceConstant.DEFAULT_MIN_SIZE;

    /**
     * 最大连接数
     */
    protected int maxSize = DataSourceConstant.DEFAULT_MAX_SIZE;

    /**
     * 获取连接的最大等待时间
     */
    protected long maxWait = DataSourceConstant.DEFAULT_MAX_WAIT;

    /**
     * 验证数据库连接查询语句
     */
    protected String validQuery = DataSourceConstant.DEFAULT_VALID_QUERY;

    /**
     * 验证超时连接
     */
    protected int validTimeOutSeconds = DataSourceConstant.DEFAULT_VALID_TIME_OUT_SECONDS;

    /**
     * 空闲时验证
     */
    protected boolean testOnIdle = DataSourceConstant.DEFAULT_TEST_ON_IDLE;

    /**
     * 空闲时自动验证
     */
    protected long autoTestOnIdleSeconds = DataSourceConstant.DEFAULT_AUTO_TEST_ON_IDLE_SECONDS;


    @Override
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public void setValidQuery(String validQuery) {
        this.validQuery = validQuery;
    }

    @Override
    public void setValidTimeOutSeconds(int validTimeOutSeconds) {
        this.validTimeOutSeconds = validTimeOutSeconds;
    }

    @Override
    public void setTestOnIdle(boolean testOnIdle) {
        this.testOnIdle = testOnIdle;
    }

    @Override
    public void setAutoTestOnIdle(long autoTestOnIdleSeconds) {
        this.autoTestOnIdleSeconds = autoTestOnIdleSeconds;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }
}
