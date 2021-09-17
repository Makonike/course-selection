package com.zxd.www.util.ioc.annotation;

import java.lang.annotation.*;

/**
 * 为变量注入对象
 * @author Makonike
 * @date 2021-09-15 11:56
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
