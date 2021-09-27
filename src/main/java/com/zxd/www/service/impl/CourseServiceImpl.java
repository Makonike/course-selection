package com.zxd.www.service.impl;

import com.zxd.www.dao.CourseDao;
import com.zxd.www.po.Course;
import com.zxd.www.po.CourseDel;
import com.zxd.www.service.CourseService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程service实现类
 * @author Makonike
 * @date 2021-09-19 16:16
 **/
@Component
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Override
    public boolean addCourse(Course course) {
        return courseDao.insertCourse(course) != 0;
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDao.updateCourse(course) != 0;
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return courseDao.deleteCourse(courseId) != 0;
    }

    @Override
    public Course getById(Integer courseId) {
        return courseDao.getCourseByCourseId(courseId);
    }

    @Override
    public List<Course> getByName(String courseName) {
        return courseDao.getCourseByName(courseName);
    }

    @Override
    public List<Course> getCourseAll() {
        return courseDao.getCourseList();
    }

    @Override
    public CourseDel getCourseDelById(Integer courseDelId) {
        return courseDao.getCourseDelById(courseDelId);
    }

    /**
     * 判断选课是否可行
     */
    @Override
    public boolean validCanNotSelectCourse(CourseDel courseDel){
        // 一开始 - true
        boolean isStart = courseDel.getStartTime().compareTo(LocalDateTime.now()) < 0;
        // 已过期 - true
        boolean isExp = courseDel.getExpTime().compareTo(LocalDateTime.now()) < 0;
        // 已满员 - true
        boolean isMaxNum = courseDel.getNowNum() >= courseDel.getMaxNum();
        System.out.println(isStart);
        if(isStart && !isExp && !isMaxNum){
            return true;
        }
        if(isExp){
            expCourseDel(courseDel.getCoursedelId());
        }
        return false;
    }

    @Override
    public boolean validCanNotCancelCourse(CourseDel courseDel){
        boolean isExp = courseDel.getExpTime().compareTo(LocalDateTime.now()) < 0;
        Integer status = courseDel.getStatus();
        // 双重保证，防止最后一名选课的同学可以过期退选
        if(status == 1 || isExp){
            return false;
        }
        return courseDel.getNowNum() > 0;
    }

    private void expCourseDel(Integer courseDelId){
        courseDao.expCourseDel(courseDelId);
    }

    @Override
    public boolean addCourseForCourseDel(Integer courseDelId){
        return courseDao.incrCourseDelNum(courseDelId) != 0;
    }

    @Override
    public boolean cancelCourseForCourseDel(Integer courseDelId){
        return courseDao.decrCourseDelNum(courseDelId) != 0;
    }

    @Override
    public List<Integer> getAllCourseByStudentId(Integer studentId) {
        return courseDao.getAllCourseByStudentId(studentId);
    }

    @Override
    public List<CourseDel> getCourseDelAll(){
        return courseDao.getCourseDelList();
    }

    @Override
    public boolean deleteCourseDel(Integer courseDelId){
        return courseDao.deleteCourseDel(courseDelId) != 0;
    }

    @Override
    public boolean updateCourseDel(CourseDel courseDel) {
        return courseDao.updateCourseDel(courseDel) != 0;
    }
}
