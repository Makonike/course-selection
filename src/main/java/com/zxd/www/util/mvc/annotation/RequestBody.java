package com.zxd.www.util.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author Makonike
 * @date 2021-09-23 17:28
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBody {
    String value() default "";
}
