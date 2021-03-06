package com.zxd.www.service;

import com.zxd.www.po.Admin;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-13 19:25
 **/
public interface AdminService {

    boolean addAdmin(String adminName, String adminPassword);

    boolean updateAdmin(Admin admin);

    boolean deleteAdmin(Integer adminId);

    Admin getAdminByName(String adminName);

    List<Admin> getAdminList();

    boolean isExists(String adminName);
}
