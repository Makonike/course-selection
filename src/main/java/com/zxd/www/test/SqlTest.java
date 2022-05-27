package com.zxd.www.test;

import com.zxd.www.dao.UserDao;
import com.zxd.www.po.User;
import com.zxd.www.service.UserService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.util.mvc.helper.LoaderHelper;


/**
 * @author Makonike
 * @date 2021-09-19 16:12
 **/
@Component
public class SqlTest {

    @Autowired
    static UserService userService;

    public static void main(String[] args) {
        String path = "com.zxd.www";
        LoaderHelper.init(path);
        System.out.println(userService.isExists("31200051180"));
    }
}
