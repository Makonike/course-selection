package com.zxd.www.global.filter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zxd.www.constant.JwtConstant;
import com.zxd.www.global.po.JsonResponse;
import com.zxd.www.util.JwtUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 登录拦截器
 * @author Makonike
 * @date 2021-09-21 14:44
 **/
@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String requestPath = null;
        // 关键处, 跨域支持
        resp.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        // 获取请求路径
        if (contextPath != null && contextPath.length() > 0) {
            requestPath = uri.substring(contextPath.length());
        }
        // 过滤链接
        if (FilterExcludeUrl.pattenURL.contains(requestPath)){
            chain.doFilter(request, response);
            return;
        }
        Optional<String> token = Optional.ofNullable(req.getHeader(JwtConstant.TOKEN_HEADER_KEY));
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        // 带有token
        if(token.isPresent()){
            // token验证通过，放行
            if(JwtUtils.verifyToken(token.get())){
                chain.doFilter(request, response);
                return;
            }
            // token失效,暂时只返回这个异常提示
            String jsonResponse = JSON.toJSONString(new JsonResponse().unauthorized().message("token已失效，请重新登录！"), SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullStringAsEmpty);
            resp.getWriter().println(jsonResponse);
            return;
        }
        String jsonResponse = JSON.toJSONString(new JsonResponse().unauthorized().message("请登录后再试！"), SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty);
        resp.getWriter().println(jsonResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
