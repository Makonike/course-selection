package com.zxd.www.service;

import com.zxd.www.po.Student;

import java.util.List;

/**
 * 学生service接口
 * @author Makonike
 * @date 2021-09-13 14:09
 **/
public interface StudentService {

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(Integer studentId);

    Student getStudentByNo(String studentNo);

    Student getStudentByUserId(Integer userId);

    List<Student> getStudentByName(String studentName);

    List<Student> getStudentList();

    boolean addCourse(Integer userId, Integer courseId);

    boolean addCourseForStudent(Integer studentId, Integer courseId);
}
