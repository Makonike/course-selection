package com.zxd.www.dao;

import com.zxd.www.po.Student;

import java.util.List;

/**
 * 数据交互接口-student
 * @author Makonike
 * @date 2021-09-12 23:11
 **/
public interface StudentDao {

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(Integer studentId);

    Student getStudentByNo(String studentNo);

    Student getStudentByUserId(Integer userId);

    List<Student> getStudentByName(String studentName);

    List<Student> getStudentList();

    int insertConnStudentCourse(Integer studentId, Integer courseId);

    int cancelConnStudentCourse(Integer studentId, Integer courseId);

    int incrStudentCourse(Integer studentId);

    int decrStudentCourse(Integer studentId);
}
