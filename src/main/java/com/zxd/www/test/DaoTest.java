package com.zxd.www.test;

import com.zxd.www.dao.AdminDao;
import com.zxd.www.dao.impl.AdminDaoImpl;
import com.zxd.www.po.Admin;
import org.junit.Test;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-16 0:43
 **/
public class DaoTest {

    AdminDao adminDao = new AdminDaoImpl();

    @Test
    public void adminDaoTest(){
        List<Admin> adminList = adminDao.getAdminList();
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }
}
