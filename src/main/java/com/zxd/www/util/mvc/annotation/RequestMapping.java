package com.zxd.www.util.mvc.annotation;

import java.lang.annotation.*;

/**
 * 请求方法注解
 * @author Makonike
 * @date 2021-09-18 14:04
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {

    enum RequestMethod {
        /**
         * get方法
         */
        GET,
        POST,
        PUT,
        DELETE
    }

    /**
     * 请求路径
     */
    String path();

    /**
     * 请求方法
     */
    String method();
}
