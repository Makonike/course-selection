package com.zxd.www.service.impl;

import com.zxd.www.dao.UserDao;
import com.zxd.www.po.User;
import com.zxd.www.service.UserService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;

/**
 * @author Makonike
 * @date 2021-09-20 17:00
 **/
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(String userName, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        return userDao.insertUser(user) != 0;
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public User updateUser() {
        return null;
    }

    @Override
    public boolean isExists(String userName) {
        User user = userDao.getUserByUserName(userName);
        return user != null;
    }
}
