package com.zxd.www.po;

import java.time.LocalDateTime;

/**
 * 选课信息实体类
 * @author Makonike
 * @date 2021-09-13 20:06
 **/
public class CourseDel {

    private Integer coursedelId;
    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 开始选课时间
     */
    private LocalDateTime startTime;
    /**
     * 结束选课时间
     */
    private LocalDateTime expTime;
    /**
     * 最大可选人数
     */
    private Integer maxNum;
    /**
     * 目前已选人数
     */
    private Integer nowNum;
    /**
     * 选课信息详情
     */
    private String coursedelDesc;
    /**
     * 选课状态 0-生效 1-过期
     */
    private Integer status;

    public CourseDel() {
    }

    public CourseDel(Integer coursedelId, Integer courseId, String courseName, LocalDateTime startTime, LocalDateTime expTime, Integer maxNum, Integer nowNum, String coursedelDesc, Integer status) {
        this.coursedelId = coursedelId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.startTime = startTime;
        this.expTime = expTime;
        this.maxNum = maxNum;
        this.nowNum = nowNum;
        this.coursedelDesc = coursedelDesc;
        this.status = status;
    }

    public Integer getCoursedelId() {
        return coursedelId;
    }

    public void setCoursedelId(Integer coursedelId) {
        this.coursedelId = coursedelId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getExpTime() {
        return expTime;
    }

    public void setExpTime(LocalDateTime expTime) {
        this.expTime = expTime;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getNowNum() {
        return nowNum;
    }

    public void setNowNum(Integer nowNum) {
        this.nowNum = nowNum;
    }

    public String getCoursedelDesc() {
        return coursedelDesc;
    }

    public void setCoursedelDesc(String coursedelDesc) {
        this.coursedelDesc = coursedelDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseDel{" +
                "coursedelId=" + coursedelId +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", startTime=" + startTime +
                ", expTime=" + expTime +
                ", maxNum=" + maxNum +
                ", nowNum=" + nowNum +
                ", coursedelDesc='" + coursedelDesc + '\'' +
                ", status=" + status +
                '}';
    }
}
