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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger("loginLog");

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
            logger.info("添加管理员【" + adminName + "】成功！");
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
            logger.info("管理员登录失败，管理员账号【" + adminName + "】不存在！");
            return new JsonResponse().notFound().message("用户名不存在！");
        }
        if(!StringUtils.validPassword(password, admin.getAdminPassword(), admin.getAdminSalt())){
            logger.info("管理员【" + adminName + "】登录失败，密码错误！");
            return new JsonResponse().unauthorized().message("密码错误！");
        }
        Map<String, Object> result = new HashMap<>(1);
        String token = JwtUtils.generateAdminJwt(admin);
        result.put("token", token);
        logger.info("管理员【" + adminName + "】登录成功！");
        return new JsonResponse().data(result);
    }

    @RequestMapping(path = "/admin/all")
    public JsonResponse getAll(){
        return new JsonResponse().data(adminService.getAdminList());
    }

    @RequestMapping(path = "/admin/del", method = RequestMethodConstant.DELETE)
    public JsonResponse deleteAdminById(@RequestParam("adminId") Integer adminId){
        if(adminService.deleteAdmin(adminId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除管理员失败！");
    }


}
