package com.zxd.www.po;

import java.time.LocalDateTime;

/**
 * @author Makonike
 * @date 2021-09-10 11:08
 **/
public class Admin {

    private Integer adminId;
    private String adminName;
    private String adminSalt;
    private String adminPassword;
    private LocalDateTime createTime;
    private Integer removed;

    public Admin() {
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
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
