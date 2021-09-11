package com.zxd.www.dpool.config;

/**
 * @author Makonike
 * @date 2021-09-11 10:05
 **/
public interface IConfig {

    /**
     * 设置数据库连接驱动
     * @param driverClass 驱动
     */
    void setDriverClass(final String driverClass);

    /**
     * 设置JdbcUtil
     * @param jdbcUtil JdbcUtil
     */
    void setJdbcUtil(final String jdbcUtil);

    /**
     * 设置用户信息
     * @param user user
     */
    void setUsername(final String username);

    /**
     * 设置密码
     * @param password password
     */
    void setPassword(final String password);
}
