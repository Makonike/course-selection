package com.zxd.www.po;

import java.util.Date;

/**
 * 班级实体类
 * @author Makonike
 * @date 2021-09-10 11:22
 **/
public class Class {

    private Integer classId;
    private String className;
    /**
     * 学院id
     */
    private Integer instituteId;
    /**
     * 年级id
     */
    private Integer gradeId;
    private Date createTime;
    private Integer removed;

    public Class() {
    }

    public Class(Integer classId, String className, Integer instituteId, Integer gradeId, Date createTime, Integer removed) {
        this.classId = classId;
        this.className = className;
        this.instituteId = instituteId;
        this.gradeId = gradeId;
        this.createTime = createTime;
        this.removed = removed;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", instituteId=" + instituteId +
                ", gradeId=" + gradeId +
                ", createTime=" + createTime +
                ", removed=" + removed +
                '}';
    }
}
