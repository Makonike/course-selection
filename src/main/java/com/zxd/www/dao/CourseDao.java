package com.zxd.www.dao;

import com.zxd.www.po.Course;
import com.zxd.www.po.Student;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-17 20:57
 **/
public interface CourseDao {

    int insertCourse(Course course);

    int updateCourse(Course course);

    int deleteCourse(Integer courseId);

    Course getCourseByCourseId(Integer courseId);

    List<Course> getCourseByName(String courseName);

    List<Course> getCourseList();

}
