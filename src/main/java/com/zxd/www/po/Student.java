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
    private Integer classId;
    private Integer selectedCount;
    private Integer removed;

    public Student() {
    }

    public Student(Integer studentId, String studentNo, String studentName, Integer studentSex, Integer classId, Integer selectedCount, Integer removed) {
        this.studentId = studentId;
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.classId = classId;
        this.selectedCount = selectedCount;
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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(Integer selectedCount) {
        this.selectedCount = selectedCount;
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
                ", classId=" + classId +
                ", selectedCount=" + selectedCount +
                ", removed=" + removed +
                '}';
    }
}
