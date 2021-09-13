package com.zxd.www.dao.impl;

import com.zxd.www.dao.AdminDao;
import com.zxd.www.dao.BaseDao;
import com.zxd.www.po.Admin;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-13 14:40
 **/
public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public int insertAdmin(Admin admin) {
        //language=sql
        String sql = " INSERT INTO `t_admin` " +
                " (`admin_name`, `admin_salt`, `admin_password`, `create_time`) " +
                " VALUES(?, ?, ?, ?) ";
        return update(sql, admin.getAdminName(), admin.getAdminSalt(), admin.getAdminPassword(), admin.getCreateTime());
    }

    @Override
    public int updateAdmin(Admin admin) {
        //language=sql
        String sql = " UPDATE `t_admin` " +
                " SET `admin_name` = ?, `admin_salt` = ?, `admin_password` = ? " +
                " WHERE `admin_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, admin.getAdminName(), admin.getAdminSalt(), admin.getAdminPassword(), admin.getAdminId());
    }

    @Override
    public int deleteAdmin(Integer adminId) {
        //language=sql
        String sql = " UPDATE `t_admin` " +
                " SET `removed` = 1 " +
                " WHERE `admin_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, adminId);
    }

    @Override
    public Admin getAdminByName(String adminName) {
        //language=sql
        String sql = " SELECT `admin_id`, `admin_name`, `admin_salt`, `admin_password`, `create_time`" +
                " FROM `t_admin` " +
                " WHERE `admin_name` = ? " +
                " AND `removed` = 0";
        return getOne(Admin.class, sql, adminName);
    }

    @Override
    public List<Admin> getAdminList() {
        //language=sql
        String sql = " SELECT `admin_id`, `admin_name`, `admin_salt`, `admin_password`, `create_time` " +
                " FROM `t_admin` " +
                " WHERE `removed` = 0 " +
                " ORDER BY `create_time` ASC ";
        return getList(Admin.class, sql);
    }
}
