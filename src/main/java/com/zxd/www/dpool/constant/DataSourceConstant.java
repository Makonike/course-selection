package com.zxd.www.dpool.constant;

/**
 * @author Makonike
 * @date 2021-09-11 9:38
 **/
public class DataSourceConstant {

    public DataSourceConstant() {
    }

    public static final String DRIVER_CLASS = "driver";
    public static final String JDBC_URL = "url";
    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";
    public static final String MAX_SIZE = "maxSize";
    public static final String MIN_SIZE = "minSize";



    /**
     * 默认的最小连接数
     */
    public static final int DEFAULT_MIN_SIZE = 5;

    /**
     * 默认最大的连接数
     */
    public static final int DEFAULT_MAX_SIZE = 15;

    /**
     * 默认最大的等待毫秒数 - 1mins
     */
    public static final int DEFAULT_MAX_WAIT = 60 * 1000;

    /**
     * 默认验证查询的语句
     */
    public static final String DEFAULT_VALID_QUERY = "select 1";

    /**
     * 默认的验证的超时时间
     */
    public static final int DEFAULT_VALID_TIME_OUT_SECONDS = 5;

    /**
     * 默认开启闲暇时间校验
     */
    public static final boolean DEFAULT_TEST_ON_IDLE = true;

    /**
     * 自动校验 - 1mins一次
     */
    public static final long DEFAULT_AUTO_TEST_ON_IDLE_SECONDS = 60;
}
