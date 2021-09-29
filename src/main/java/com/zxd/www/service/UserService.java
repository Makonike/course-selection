package com.zxd.www.service;

import com.zxd.www.po.User;

/**
 * @author Makonike
 * @date 2021-09-20 16:58
 **/
public interface UserService {


    boolean addUser(String userName, String password);

    User findByUserName(String userName);

    User updateUser();

    boolean isExists(String userName);

}
