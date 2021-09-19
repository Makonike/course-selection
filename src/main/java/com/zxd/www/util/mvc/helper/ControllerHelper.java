package com.zxd.www.util.mvc.helper;

import com.zxd.www.util.ioc.factory.BeanFactory;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.bean.MappingHandler;
import com.zxd.www.util.mvc.bean.Request;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 处理request与handler映射关系类
 * @author Makonike
 * @date 2021-09-18 14:55
 **/
public class ControllerHelper {

    /**
     * 存放request与处理请求的handler的映射关系
     */
    private static final Map<Request, MappingHandler> REQUEST_MAPPING_HANDLER_MAP = new HashMap<>();

    static {
        Map<Class<?>, Object> com = BeanFactory.get();
        Set<Class<?>> classes = com.keySet();
        // 获取所有带有Controller注解的类，作请求和handler的映射
        ArrayList<Class<?>> controllerClasses = classes.stream()
                .filter(c -> c.isAnnotationPresent(Controller.class))
                // 转换为集合
                .collect(Collectors.toCollection(ArrayList::new));
        if (controllerClasses.size() > 0) {
            initRequestMap(controllerClasses);
        }
    }

    /**
     * 作请求和handler的映射
     * @param controllerClasses 所有带有Controller注解的类
     */
    private static void initRequestMap(ArrayList<Class<?>> controllerClasses) {
        for (Class<?> controllerClass : controllerClasses) {
            // 获取所有方法
            Method[] methods = controllerClass.getDeclaredMethods();
            if (methods.length > 0) {

                Arrays.stream(methods)
                        // 过滤得到 有RequestMapping注解的方法
                        .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                        .forEach(method -> {
                            RequestMapping rm = method.getAnnotation(RequestMapping.class);
                            // 获得请求路径
                            String uri = rm.path();
                            // 封装为request
                            Request request = new Request(rm.method(), uri);

                            String[] args = Arrays.stream(method.getParameters())
                                    .filter(parameter -> parameter.isAnnotationPresent(RequestParam.class))
                                    .map(parameter -> parameter.getAnnotation(RequestParam.class).value())
                                    .toArray(String[]::new);
                            // 封装为handler
                            MappingHandler handler = new MappingHandler(uri, controllerClass, method, args);

                            REQUEST_MAPPING_HANDLER_MAP.put(request, handler);
                        });
            }
        }
    }

    /**
     * 获取handler
     * @param requestMethod 请求方法
     * @param requestPath 请求路径
     * @return MappingHandler
     */
    public static MappingHandler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return REQUEST_MAPPING_HANDLER_MAP.get(request);
    }


}
