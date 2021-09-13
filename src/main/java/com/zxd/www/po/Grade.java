package com.zxd.www.po;

/**
 * 年级实体类
 * @author Makonike
 * @date 2021-09-13 20:01
 **/
public class Grade {

    private Integer gradeId;
    /**
     * 年级名称
     */
    private String gradeName;
    /**
     * 年级详情
     */
    private String gradeDesc;

    public Grade() {
    }

    public Grade(Integer gradeId, String gradeName, String gradeDesc) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", gradeDesc='" + gradeDesc + '\'' +
                '}';
    }
}
