package com.zxd.www.test;

import com.zxd.www.dao.AdminDao;
import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.Admin;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-19 0:32
 **/
@Controller
public class TestController {

    @Autowired
    AdminDao adminDao;

    @RequestMapping(path = "/doTest")
    public JsonResponse test1(@RequestParam("param") String param){
        List<Admin> adminList = adminDao.getAdminList();
        System.out.println(param);
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
        return new JsonResponse().data(adminList);
    }

    @RequestMapping(path = "/test")
    public JsonResponse test2(){
        return new JsonResponse();
    }

}
