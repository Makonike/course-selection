package com.zxd.www.dao.impl;

import com.zxd.www.dao.BaseDao;
import com.zxd.www.dao.UserDao;
import com.zxd.www.po.User;
import com.zxd.www.util.ioc.annotation.Component;

/**
 * @author Makonike
 * @date 2021-09-20 16:48
 **/
@Component
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int insertUser(User user) {
        //language=sql
        String sql = " INSERT INTO `t_user` " +
                    " (`username`, `password`, `salt`)" +
                    " VALUES(?, ?, ?) ";
        return update(sql, user.getUsername(), user.getPassword()
                    , user.getSalt());
    }

    @Override
    public int updateUser(User user) {
        //language=sql
        String sql = " UPDATE `t_user` " +
                    " SET `password` = ?, `salt` = ? " +
                    " WHERE `user_id` = ? ";
        return update(sql, user.getPassword(), user.getSalt()
                    , user.getUserId());
    }

    @Override
    public User getUserByUserName(String userName) {
        //language=sql
        String sql = " SELECT `user_id`, `username`, `password`" +
                    " , `salt` FROM `t_user`" +
                    " WHERE `username` = ? ";
        return getOne(User.class, sql, userName);
    }
}
