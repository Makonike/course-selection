package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.Admin;
import com.zxd.www.service.AdminService;
import com.zxd.www.util.JwtUtils;
import com.zxd.www.util.StringUtils;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.constant.RequestMethodConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Makonike
 * @date 2021-09-24 13:56
 **/
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 添加管理员
     * @param adminName 账户
     * @param password 密码
     */
    @RequestMapping(path = "/admin/register", method = RequestMethodConstant.POST)
    public JsonResponse register(@RequestParam(value = "adminName") String adminName,
                                 @RequestParam(value = "password") String password){
        if(adminService.isExists(adminName)){
            return new JsonResponse().unauthorized().message("用户已存在！");
        }
        if(adminService.addAdmin(adminName, password)){
            return new JsonResponse();
        }
        return new JsonResponse().internalServerError().message("请求错误，请联系管理员修复！");
    }

    /**
     * 管理员登录功能
     * @param adminName 管理员账户
     * @param password 密码
     */
    @RequestMapping(path = "/admin/login", method = RequestMethodConstant.POST)
    public JsonResponse login(@RequestParam(value = "adminName") String adminName,
                              @RequestParam(value = "password") String password){
        Admin admin = adminService.getAdminByName(adminName);

        if(null == admin){
            return new JsonResponse().notFound().message("用户名不存在！");
        }
        if(!StringUtils.validPassword(password, admin.getAdminPassword(), admin.getAdminSalt())){
            return new JsonResponse().unauthorized().message("密码错误！");
        }
        Map<String, Object> result = new HashMap<>(1);
        String token = JwtUtils.generateAdminJwt(admin);
        result.put("token", token);
        return new JsonResponse().data(result);
    }

}
