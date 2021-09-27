package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.User;
import com.zxd.www.service.UserService;
import com.zxd.www.util.JwtUtils;
import com.zxd.www.util.StringUtils;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.constant.RequestMethodConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Makonike
 * @date 2021-09-21 15:59
 **/
@Controller
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger("loginLog");


    @RequestMapping(path = "/user/register", method = RequestMethodConstant.POST)
    public JsonResponse register(@RequestParam(value = "userName") String userName,
                              @RequestParam(value = "password") String password){
        if(userService.isExists(userName)){
            return new JsonResponse().unauthorized().message("用户已存在！");
        }
        if(userService.addUser(userName, password)){
            logger.info("用户【" + userName + "】注册成功！");
            return new JsonResponse();
        }
        return new JsonResponse().internalServerError().message("请求错误，请联系管理员修复！");
    }

    @RequestMapping(path = "/user/login", method = RequestMethodConstant.POST)
    public JsonResponse login(@RequestParam(value = "userName") String userName,
                             @RequestParam(value = "password") String password){
        User user = userService.findByUserName(userName);
        if(null == user){
            logger.info("登录失败，用户名【" + userName + "】不存在！");
            return new JsonResponse().notFound().message("用户名不存在！");
        }
        if(!StringUtils.validPassword(password, user.getPassword(), user.getSalt())){
            logger.info("用户【" + userName + "】登录失败，密码错误！");
            return new JsonResponse().unauthorized().message("密码错误！");
        }
        Map<String, Object> result = new HashMap<>(1);
        String token = JwtUtils.generateUserJwt(user);
        result.put("token", token);
        logger.info("用户【" + userName + "】登录成功！");
        return new JsonResponse().data(result);
    }
}
