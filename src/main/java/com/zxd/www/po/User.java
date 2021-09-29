package com.zxd.www.po;

/**
 * 用户实体类
 * @author Makonike
 * @date 2021-09-13 21:12
 **/
public class User {

    private Integer userId;
    /**
     * 用户登录的账号，这里是用学号来当账号登录
     */
    private String username;
    private String password;
    private String salt;

    public User() {
    }

    public User(Integer userId, String username, String password, String salt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
