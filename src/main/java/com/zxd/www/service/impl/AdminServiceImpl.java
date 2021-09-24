package com.zxd.www.service.impl;

import com.zxd.www.dao.AdminDao;
import com.zxd.www.util.StringUtils;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.po.Admin;
import com.zxd.www.service.AdminService;

import java.util.List;

/**
 * 管理员service实现类
 * @author Makonike
 * @date 2021-09-13 19:29
 **/
@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    /**
     * 添加管理员
     * @return true-成功 false-失败
     */
    @Override
    public boolean addAdmin(String adminName, String adminPassword) {
        Admin admin = new Admin();
        admin.setAdminName(adminName);
        String salt = StringUtils.generateSalt();
        adminPassword = StringUtils.addSaltPassword(adminPassword, salt);
        admin.setAdminPassword(adminPassword);
        admin.setAdminSalt(salt);
        return adminDao.insertAdmin(admin) != 0;
    }

    @Override
    public boolean isExists(String adminName) {
        Admin admin = adminDao.getAdminByName(adminName);
        return admin != null;
    }

    /**
     * 更新管理员信息
     * @param admin admin
     * @return true-成功 false-失败
     */
    @Override
    public boolean updateAdmin(Admin admin) {
        int i = adminDao.updateAdmin(admin);
        return i != 0;
    }

    /**
     * 删除管理员
     * @param adminId 管理员id
     * @return true-成功 false-失败
     */
    @Override
    public boolean deleteAdmin(Integer adminId) {
        int i = adminDao.deleteAdmin(adminId);
        return i != 0;
    }

    /**
     * 通过姓名查找管理员信息
     * 可用于后续 管理员找回密码
     * @param adminName 姓名
     * @return 管理员信息
     */
    @Override
    public Admin getAdminByName(String adminName) {
        return adminDao.getAdminByName(adminName);
    }

    /**
     * 获取所有管理员信息
     * @return 所有管理员信息
     */
    @Override
    public List<Admin> getAdminList() {
        return adminDao.getAdminList();
    }
}
