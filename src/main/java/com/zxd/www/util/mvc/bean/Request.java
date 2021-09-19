package com.zxd.www.util.mvc.bean;

import java.util.Objects;

/**
 * @author Makonike
 * @date 2021-09-18 14:45
 **/
public class Request {

    /**
     * 请求路径
     */
    private String requestPath;

    /**
     * 请求方法
     */
    private String requestMethod;

    public Request(String requestPath, String requestMethod) {
        this.requestPath = requestPath;
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(requestPath, request.requestPath) && Objects.equals(requestMethod, request.requestMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestPath, requestMethod);
    }
}
