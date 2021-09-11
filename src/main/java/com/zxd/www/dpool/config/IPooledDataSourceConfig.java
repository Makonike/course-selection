package com.zxd.www.dpool.config;


import com.zxd.www.dpool.connection.IPooledConnection;

/**
 * @author Makonike
 * @date 2021-09-11 10:08
 **/
public interface IPooledDataSourceConfig extends IDataSourceConfig {

    /**
     * 归还连接至数据库连接池
     * @param connection 连接
     */
    void returnConnection(IPooledConnection connection);

    /**
     * 设置最小空闲连接数
     * @param minSize 最小空闲连接数
     */
    void setMinSize(final int minSize);

    /**
     * 设置最大连接数
     * @param maxSize 最大连接数
     */
    void setMaxSize(final int maxSize);

    /**
     * 设置获取连接的最大等待时间
     * @param maxWait 等待时间
     */
    void setMaxWait(final long maxWait);

    /**
     * 设置验证数据库连接的查询语句
     * @param validQuery 查询语句
     */
    void setValidQuery(final String validQuery);

    /**
     * 查询超时秒数
     * @param validTimeOutSeconds 超时秒数
     */
    void setValidTimeOutSeconds(final int validTimeOutSeconds);

    /**
     * 空闲时进行校验
     * @param testOnIdle 校验
     */
    void setTestOnIdle(final boolean testOnIdle);

    /**
     * 闲暇时间自动校验
     * @param autoTestOnIdleSeconds 时间间隔
     */
    void setAutoTestOnIdle(final long autoTestOnIdleSeconds);

}
