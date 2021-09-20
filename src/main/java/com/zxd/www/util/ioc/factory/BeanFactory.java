package com.zxd.www.util.ioc.factory;

import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.util.ioc.exception.IocException;
import com.zxd.www.util.ioc.scan.ClassScanner;
import com.zxd.www.util.mvc.annotation.Controller;

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

    private static BeanFactory instance = null;

    private BeanFactory(String path) {
        ClassScanner.scanClasses(path);
        instanceBeans();
        autowiredBean();
    }

    public static void initBeanFactory(String path){
        if(instance == null){
            instance = new BeanFactory(path);
        }
    }

    public static Object getBean(Class<?> clazz){
        if(CLASSES_INSTANCE.containsKey(clazz)){
            return CLASSES_INSTANCE.get(clazz);
        }
        throw new IocException("不到该类：" + clazz.getName());
    }

    public static void instanceBeans(){
        List<String> cfn = ClassScanner.getClassFullName();
        if(cfn.size() > 0){
            for (String className : cfn) {
                try {
                    createBean(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new IocException("找不到该类：" + className , e);
                }
            }
        }
    }

    /**
     * 创建bean对象
     * @param clazz clazz
     */
    private static void createBean(Class<?> clazz){
        // 过滤掉不带Component和Controller注解的类
        if (!clazz.isAnnotationPresent(Component.class) && !clazz.isAnnotationPresent(Controller.class)) {
            return;
        }
        Object classInstance = null;
        try {
            // 实例化
            classInstance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IocException("实例化对象失败：" + clazz.getName(), e);
        }
        // 将该类放入容器
        CLASSES_INSTANCE.put(clazz, classInstance);
    }

    /**
     * 为属性注入bean, 避免注入时找不到还没放入容器的类
     */
    private static void autowiredBean(){

        for (Class<?> clazz : CLASSES_INSTANCE.keySet()) {
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
                    for (Class<?> key : BeanFactory.CLASSES_INSTANCE.keySet()) {
                        if (fieldType.isAssignableFrom(key)) {
                            fieldType = key;
                            break;
                        }
                    }
                }

                try {
                    // 注入实例
                    field.set(CLASSES_INSTANCE.get(clazz), BeanFactory.getBean(fieldType));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new IocException("注入实例失败：" + field.getName(), e);
                }
            }
        }

    }

    /**
     * 获取bean与实例关系
     * @return map
     */
    public static Map<Class<?>,Object> get(){
        return CLASSES_INSTANCE;
    }

}
