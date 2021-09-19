package com.zxd.www.util.mvc.annotation;

import java.lang.annotation.*;

/**
 * 方法参数注解
 * @author Makonike
 * @date 2021-09-18 20:12
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestParam {
    /**
     * 参数名
     */
    String value();
}
