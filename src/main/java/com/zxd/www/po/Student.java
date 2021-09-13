package com.zxd.www.po;

/**
 * 学生实体类
 * @author Makonike
 * @date 2021-09-10 10:59
 **/
public class Student {

    private Integer studentId;
    private String studentNo;
    private String studentName;
    private Integer studentSex;
    private Integer studentClassId;
    private Integer selectedCourseCount;
    private Integer removed;

    public Student() {
    }

    public Student(Integer studentId, String studentNo, String studentName, Integer studentSex, Integer studentClassId, Integer selectedCourseCount, Integer removed) {
        this.studentId = studentId;
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentClassId = studentClassId;
        this.selectedCourseCount = selectedCourseCount;
        this.removed = removed;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    public Integer getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Integer getSelectedCourseCount() {
        return selectedCourseCount;
    }

    public void setSelectedCourseCount(Integer selectedCourseCount) {
        this.selectedCourseCount = selectedCourseCount;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", studentClassId=" + studentClassId +
                ", selectedCourseCount=" + selectedCourseCount +
                ", removed=" + removed +
                '}';
    }
}
