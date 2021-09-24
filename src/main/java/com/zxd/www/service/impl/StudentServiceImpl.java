package com.zxd.www.service.impl;

import com.zxd.www.dao.StudentDao;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.po.Student;
import com.zxd.www.service.StudentService;

import java.util.List;

/**
 * 学生service实现类
 * @author Makonike
 * @date 2021-09-13 14:09
 **/
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    /**
     * 插入学生信息
     * @param student student
     * @return true-成功 false-失败
     */
    @Override
    public boolean addStudent(Student student) {
        int i = studentDao.insertStudent(student);
        return i != 0;
    }

    /**
     * 更新学生信息
     * @param student student
     * @return true-成功 false-失败
     */
    @Override
    public boolean updateStudent(Student student) {
        int i = studentDao.updateStudent(student);
        return i != 0;
    }

    /**
     * 删除学生
     * @param studentId 学生id
     * @return true-成功 false-失败
     */
    @Override
    public boolean deleteStudent(Integer studentId) {
        int i = studentDao.deleteStudent(studentId);
        return i != 0;
    }

    /**
     * 通过学号查找学生
     * @param studentNo 学号
     * @return 学生
     */
    @Override
    public Student getStudentByNo(String studentNo) {
        return studentDao.getStudentByNo(studentNo);
    }

    @Override
    public Student getStudentByUserId(Integer userId) {
        return studentDao.getStudentByUserId(userId);
    }

    /**
     * 实现对学生姓名的模糊查询
     * @param studentName 学生姓名
     * @return List - Student
     */
    @Override
    public List<Student> getStudentByName(String studentName) {
        return studentDao.getStudentByName(studentName);
    }

    /**
     * 查找所有学生
     * @return 所有学生
     */
    @Override
    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }

    @Override
    public boolean addCourse(Integer userId, Integer courseId){
        Student student = getStudentByUserId(userId);
        student.setSelectedCourseCount(student.getSelectedCourseCount() + 1);
        boolean a = updateStudent(student);
        boolean b = addCourseForStudent(student.getStudentId(), courseId);
        return a && b;
    }

    @Override
    public boolean addCourseForStudent(Integer studentId, Integer courseId) {
        int i = studentDao.insertConnStudentCourse(studentId, courseId);
        return i != 0;
    }

}
