package com.zxd.www.util.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.util.ioc.factory.BeanFactory;
import com.zxd.www.util.mvc.bean.MappingHandler;
import com.zxd.www.util.mvc.bean.ParserHandler;
import com.zxd.www.util.mvc.constant.MvcConstant;
import com.zxd.www.util.mvc.exception.MvcException;
import com.zxd.www.util.mvc.helper.ControllerHelper;
import com.zxd.www.util.mvc.helper.LoaderHelper;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;
import java.util.Properties;

/**
 * 请求分发器，分发请求，降低耦合
 * @author Makonike
 * @date 2021-09-17 21:54
 **/
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 请求的方法
        String requestMethod = req.getMethod().toLowerCase(Locale.ENGLISH);
        // 请求uri
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String requestPath = null;
        // 获取请求路径
        if (contextPath != null && contextPath.length() > 0) {
            requestPath = uri.substring(contextPath.length());
        }

        MappingHandler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            // 获取请求controller的class
            Class<?> controllerClass = handler.getControllerClass();
            // 获取bean对象
            Object controllerBean = BeanFactory.getBean(controllerClass);
            // 获取参数名
            ParserHandler parser = new ParserHandler(req, handler);
            // 解析请求
            parser.parseRequest();
            try {
                // 调用方法
                JsonResponse invoke = (JsonResponse) handler.getMethod().invoke(controllerBean, parser.getParams());
                // 转为json字符串
                String jsonResponse = JSON.toJSONString(invoke, SerializerFeature.WriteMapNullValue,
                        SerializerFeature.WriteNullStringAsEmpty);
                // 返回json字符串
                resp.getWriter().println(jsonResponse);
            } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                e.printStackTrace();
                throw new MvcException("调用方法失败：" + handler.getMethod().getName(), e);
            }
        }
    }

    @Override
    public void init(ServletConfig config) {
        Properties properties = new Properties();
        InputStream in = DispatcherController.class.getClassLoader().getResourceAsStream(MvcConstant.APPLICATION_CONFIG);
        try {
            // 读取配置文件
            properties.load(in);
            LoaderHelper.init(properties.getProperty(MvcConstant.PACKAGE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MvcException("读取配置文件失败：" + MvcConstant.APPLICATION_CONFIG, e);
        } finally {
            if(null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
