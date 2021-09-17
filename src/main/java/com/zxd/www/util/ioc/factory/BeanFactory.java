package com.zxd.www.util.ioc.factory;

import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.util.ioc.scan.ClassScanner;

import java.lang.reflect.Field;
import java.util.*;

/**
 * bean工厂，用于类扫描和bean初始化、创建实例和注入实例
 * 非懒汉式加载，获取的bean均为 "单例"
 * @author Makonike
 * @date 2021-09-15 17:13
 **/
public class BeanFactory {

    private static final Map<Class<?>,Object> CLASSES_INSTANCE = new HashMap<>();

    public static Object getBean(Class<?> clazz){
        return CLASSES_INSTANCE.get(clazz);
    }

    public static void instanceBeans(){
        List<String> cfn = ClassScanner.getClassFullName();
        if(cfn.size() > 0){
            for (String className : cfn) {
                try {
                    createBean(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void createBean(Class<?> clazz){
        // 过滤掉不带Component注解的类
        if (!clazz.isAnnotationPresent(Component.class)) {
            return;
        }
        Object classInstance = null;
        try {
            // 实例化
            classInstance = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Field field : clazz.getDeclaredFields()) {
            // 查询需要自动注入的字段属性
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            // 获取字段类型
            Class<?> fieldType = field.getType();
            // 开启暴力反射
            field.setAccessible(true);
            if (fieldType.isInterface()) {
                // 如果依赖的类型是接口，则查询其实现类,
                // class1.isAssignableFrom(class2) = true; class1 可以从 class2 赋值，代表class2是class1类型，可分配class2对象给class1
                for (Class<?> key : BeanFactory.CLASSES_INSTANCE.keySet()) {
                    if (fieldType.isAssignableFrom(key)) {
                        fieldType = key;
                        break;
                    }
                }
            }
            try {
                // 注入实例
                field.set(classInstance, BeanFactory.getBean(fieldType));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        // 将该类放入容器
        CLASSES_INSTANCE.put(clazz, classInstance);
    }

    /**
     * 获取bean与实例关系
     * @return map
     */
    public static Map<Class<?>,Object> get(){
        return CLASSES_INSTANCE;
    }

}
