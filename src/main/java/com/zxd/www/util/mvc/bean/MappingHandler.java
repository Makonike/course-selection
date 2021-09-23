package com.zxd.www.util.mvc.bean;

import java.lang.reflect.Method;

/**
 * 方法处理器
 * @author Makonike
 * @date 2021-09-18 14:10
 **/
public class MappingHandler {

    private String uri;
    private Class<?> controllerClass;
    private Method method;

    public MappingHandler(String uri, Class<?> controllerClass, Method method) {
        this.uri = uri;
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "MappingHandler{" +
                "uri='" + uri + '\'' +
                ", controllerClass=" + controllerClass +
                ", method=" + method +
                '}';
    }
}
