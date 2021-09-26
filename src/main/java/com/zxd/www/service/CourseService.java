package com.zxd.www.service;

import com.zxd.www.po.Course;
import com.zxd.www.po.CourseDel;

import java.util.List;

/**
 * 课程service接口
 * @author Makonike
 * @date 2021-09-19 16:13
 **/
public interface CourseService {

    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(Integer courseId);

    Course getById(Integer courseId);

    List<Course> getByName(String courseName);

    List<Course> getCourseAll();

    CourseDel getCourseDelById(Integer courseDelId);

    boolean validCanNotSelectCourse(CourseDel courseDel);

    boolean addCourseForCourseDel(Integer courseDelId);

    List<CourseDel> getCourseDelAll();

    boolean validCanNotCancelCourse(CourseDel courseDel);

    boolean cancelCourseForCourseDel(Integer courseDelId);

    List<Integer> getAllCourseByStudentId(Integer studentId);

}
