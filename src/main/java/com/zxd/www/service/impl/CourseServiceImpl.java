package com.zxd.www.service.impl;

import com.zxd.www.dao.CourseDao;
import com.zxd.www.po.Course;
import com.zxd.www.service.CourseService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;

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
    public int addCourse(Course course) {
        return courseDao.insertCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public int deleteCourse(Integer courseId) {
        return courseDao.deleteCourse(courseId);
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
    public List<Course> getAll() {
        return courseDao.getCourseList();
    }
}
