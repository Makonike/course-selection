package com.zxd.www.dao;

import com.zxd.www.po.User;

/**
 * @author Makonike
 * @date 2021-09-20 16:46
 **/
public interface UserDao {

    int insertUser(User user);

    /**
     * 用户修改密码
     */
    int updateUser(User user);

    User getUserByUserName(String userName);

}
