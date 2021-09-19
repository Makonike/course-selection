package com.zxd.www.service;

import com.zxd.www.po.Course;

import java.util.List;

/**
 * 课程service接口
 * @author Makonike
 * @date 2021-09-19 16:13
 **/
public interface CourseService {

    int addCourse(Course course);

    int updateCourse(Course course);

    int deleteCourse(Integer courseId);

    Course getById(Integer courseId);

    List<Course> getByName(String courseName);

    List<Course> getAll();

}
