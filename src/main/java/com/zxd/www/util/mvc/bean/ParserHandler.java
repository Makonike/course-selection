package com.zxd.www.util.mvc.bean;

import com.zxd.www.util.JsonUtils;
import com.zxd.www.util.mvc.annotation.RequestBody;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.helper.RequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Makonike
 */
public class ParserHandler {

    private final HttpServletRequest request;

    private final MappingHandler mappingHandler;

    private Object[] params;

    public Object[] getParams() {
        return params;
    }

    public ParserHandler(HttpServletRequest request, MappingHandler handler) {
        this.request = request;
        this.mappingHandler = handler;
    }

    /**
     * 解析请求
     */
    public void parseRequest() throws IOException {
        parseRequestParam(mappingHandler.getMethod());
    }

    /**
     * 注入参数
     * @param method 方法
     */
    private void parseRequestParam(Method method) throws IOException {
        // 获取方法参数
        // 过滤请求和响应
        List<Parameter> parameters = Arrays
                .stream(method.getParameters())
                .filter((parameter) -> !parameter.getType().equals(HttpServletRequest.class) && !parameter.getType().equals(HttpServletResponse.class))
                .collect(Collectors.toList());
        List<Object> resultParams = new ArrayList<>();
        for (Parameter parameter : parameters) {
            // 解析json参数
            if(parameter.isAnnotationPresent(RequestBody.class)){
                RequestWrapper requestWrapper = new RequestWrapper(request);
                resultParams.add(parseRequestJson(requestWrapper.getBody(), parameter));
            }
            // 请求参数名称映射
            if (parameter.isAnnotationPresent(RequestParam.class)) {
                resultParams.add(parseRequestParam(parameter));
            }
        }
        // 为参数添加请求响应
        params = resultParams.toArray();
    }


    /**
     * 解析@RequestParam注解的参数
     * @param parameter 方法参数对象
     * @return 解析后的参数
     */
    private Object parseRequestParam(Parameter parameter){
        String paramName = parameter.getAnnotation(RequestParam.class).value();
        return request.getParameter(paramName);
    }

    /**
     * 解析请求中的json
     * @param parameter 方法参数
     */
    private Object parseRequestJson(String jsonString, Parameter parameter){
        return JsonUtils.fromJson(jsonString ,parameter.getType());
    }
}