package com.zxd.www.po;

import java.util.Date;

/**
 * @author Makonike
 * @date 2021-09-10 11:08
 **/
public class Admin {

    private Integer adminId;
    private String adminName;
    private String adminSalt;
    private String adminPassword;
    private Date createTime;
    private Integer removed;

    public Admin() {
    }

    public Admin(Integer adminId, String adminName, String adminSalt, String adminPassword, Date createTime, Integer removed) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminSalt = adminSalt;
        this.adminPassword = adminPassword;
        this.createTime = createTime;
        this.removed = removed;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminSalt() {
        return adminSalt;
    }

    public void setAdminSalt(String adminSalt) {
        this.adminSalt = adminSalt;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminSalt='" + adminSalt + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", createTime=" + createTime +
                ", removed=" + removed +
                '}';
    }
}
