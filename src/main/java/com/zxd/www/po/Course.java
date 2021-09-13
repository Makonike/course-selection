package com.zxd.www.po;

import java.util.Date;

/**
 * 课程实体类
 * @author Makonike
 * @date 2021-09-10 11:18
 **/
public class Course {

    private Integer courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程类型
     */
    private String courseType;
    /**
     * 任课教师名称
     */
    private String teacherName;
    /**
     * 上课时间段
     */
    private String courseTime;
    /**
     * 上课地点
     */
    private String coursePosition;
    /**
     * 学分
     */
    private Float courseCredit;
    /**
     * 学时
     */
    private Integer courseHour;
    /**
     * 开课时间
     */
    private Date createTime;
    /**
     * 课程详情
     */
    private String courseDesc;
    private Integer removed;

    public Course() {
    }

    public Course(Integer courseId, String courseName, String courseType, String teacherName, String courseTime, String coursePosition, Float courseCredit, Integer courseHour, Date createTime, String courseDesc, Integer removed) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.teacherName = teacherName;
        this.courseTime = courseTime;
        this.coursePosition = coursePosition;
        this.courseCredit = courseCredit;
        this.courseHour = courseHour;
        this.createTime = createTime;
        this.courseDesc = courseDesc;
        this.removed = removed;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCoursePosition() {
        return coursePosition;
    }

    public void setCoursePosition(String coursePosition) {
        this.coursePosition = coursePosition;
    }

    public Float getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Float courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Integer getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", coursePosition='" + coursePosition + '\'' +
                ", courseCredit=" + courseCredit +
                ", courseHour=" + courseHour +
                ", createTime=" + createTime +
                ", courseDesc='" + courseDesc + '\'' +
                ", removed=" + removed +
                '}';
    }
}
