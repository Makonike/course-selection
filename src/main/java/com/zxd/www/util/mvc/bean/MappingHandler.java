package com.zxd.www.util.mvc.bean;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 方法处理器
 * @author Makonike
 * @date 2021-09-18 14:10
 **/
public class MappingHandler {

    private String uri;
    private Class<?> controllerClass;
    private Method method;
    private  String[] args;

    public MappingHandler(String uri, Class<?> controllerClass, Method method, String[] args) {
        this.uri = uri;
        this.controllerClass = controllerClass;
        this.method = method;
        this.args = args;
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

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "MappingHandler{" +
                "uri='" + uri + '\'' +
                ", controllerClass=" + controllerClass.getName() +
                ", method=" + method +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
