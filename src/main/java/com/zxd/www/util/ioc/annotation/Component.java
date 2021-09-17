package com.zxd.www.util.ioc.annotation;

import java.lang.annotation.*;

/**
 * Component注解，将有该注解的类生成对象放入容器中
 * @author Makonike
 * @date 2021-09-15 11:54
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
