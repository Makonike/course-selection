package com.zxd.www.dao;

import com.zxd.www.po.Admin;
import com.zxd.www.po.Student;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-13 14:38
 **/
public interface AdminDao {

    int insertAdmin(Admin admin);

    int updateAdmin(Admin admin);

    int deleteAdmin(Integer adminId);

    Admin getAdminByName(String adminName);

    List<Admin> getAdminList();

}
