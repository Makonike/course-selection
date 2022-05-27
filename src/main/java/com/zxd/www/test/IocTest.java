package com.zxd.www.test;


import com.zxd.www.dao.CourseDao;
import com.zxd.www.dao.StudentDao;
import com.zxd.www.po.Grade;
import com.zxd.www.po.Institute;
import com.zxd.www.service.ClassService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.util.ioc.factory.BeanFactory;
import com.zxd.www.util.ioc.scan.ClassScanner;
import com.zxd.www.po.Admin;
import com.zxd.www.service.AdminService;
import com.zxd.www.util.mvc.helper.LoaderHelper;

import java.util.Arrays;
import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-16 23:45
 **/
@Component
public class IocTest {

    @Autowired
    private static ClassService classService;

    public static void main(String[] args){
        String path = "com.zxd.www";
        LoaderHelper.init(path);
        List<Grade> a = classService.getAllGrade();
        System.out.println(Arrays.toString(a.toArray()));
//        adminService.addAdmin("admin01","123456..");
//        adminService.addAdmin("admin02","123123..");
    }
}
