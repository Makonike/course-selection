package com.zxd.www.util.mvc.helper;

import com.zxd.www.util.ioc.factory.BeanFactory;
import com.zxd.www.util.ioc.scan.ClassScanner;

import java.util.Arrays;

/**
 * @author Makonike
 * @date 2021-09-18 19:03
 **/
public class LoaderHelper {

    public static void init(String path){
        Class<?>[] classes = {ClassScanner.class, BeanFactory.class, ControllerHelper.class};
        BeanFactory.initBeanFactory(path);
        Arrays.stream(classes).forEach(c -> {
            try {
                // 单个参数其实也是默认初始化类的
                Class.forName(c.getName(),true,Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


}
