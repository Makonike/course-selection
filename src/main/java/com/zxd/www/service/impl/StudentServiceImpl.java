package com.zxd.www.service.impl;

import com.zxd.www.dao.StudentDao;
import com.zxd.www.po.CourseDel;
import com.zxd.www.service.CourseService;
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

    @Autowired
    CourseService courseService;
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

    /**
     * 添加选课
     * @param userId 当前用户
     * @param courseDelId 选课信息id
     */
    @Override
    public boolean addCourse(Integer userId, Integer courseDelId){
        Student student = getStudentByUserId(userId);
        if(student == null){
            return false;
        }
        // 获取选课信息
        CourseDel courseDel = courseService.getCourseDelById(courseDelId);
        if(courseDel == null){
            return false;
        }
        // 判断是否可以选课
        boolean isValid = courseService.validCanNotSelectCourse(courseDel);
        if(!isValid){
            return false;
        }
        //添加关联表关系
        boolean b = addCourseForStudent(student.getStudentId(), courseDel.getCourseId());
        if(!b){
            return false;
        }
        // 使该学生选课数+1
        int a = studentDao.incrStudentCourse(student.getStudentId());

        // 使该选课信息已选人数+1
        boolean c = courseService.addCourseForCourseDel(courseDelId);
        return a != 0 && c;
    }

    @Override
    public boolean addCourseForStudent(Integer studentId, Integer courseId) {
        int i = studentDao.insertConnStudentCourse(studentId, courseId);
        return i != 0;
    }

    @Override
    public boolean cancelCourseForStudent(Integer studentId, Integer courseId) {
        int i = studentDao.cancelConnStudentCourse(studentId, courseId);
        return i != 0;
    }


    @Override
    public boolean cancelCourse(Integer userId, Integer courseDelId){

        Student student = getStudentByUserId(userId);
        if(student == null){
            return false;
        }

        // 获取选课信息
        CourseDel courseDel = courseService.getCourseDelById(courseDelId);
        if(courseDel == null){
            return false;
        }

        // 判断是否可以取消选课
        boolean isValid = courseService.validCanNotCancelCourse(courseDel);
        if(!isValid){
            return false;
        }

        //删除关联表关系
        boolean b = cancelCourseForStudent(student.getStudentId(), courseDel.getCourseId());
        if(!b){
            return false;
        }

        // 使该学生选课数-1
        int a = studentDao.decrStudentCourse(student.getStudentId());

        // 使该选课信息已选人数-1
        boolean c = courseService.cancelCourseForCourseDel(courseDelId);

        return a != 0 && c;
    }




}
