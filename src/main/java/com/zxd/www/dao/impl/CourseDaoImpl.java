package com.zxd.www.dao.impl;

import com.zxd.www.dao.BaseDao;
import com.zxd.www.dao.CourseDao;
import com.zxd.www.po.Class;
import com.zxd.www.po.Course;
import com.zxd.www.po.Student;
import com.zxd.www.util.ioc.annotation.Component;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-17 20:59
 **/
@Component
public class CourseDaoImpl extends BaseDao implements CourseDao {

    @Override
    public int insertCourse(Course course) {
        //language=sql
        String sql = " INSERT INTO `t_course` " +
                " (`course_name`, `course_type`, `teacher_name` , `course_time`" +
                " , `course_position`, `course_credit`, `course_hour`" +
                " , `course_desc`)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
        return update(sql, course.getCourseName(), course.getCourseType(), course.getTeacherName()
                , course.getCourseTime(), course.getCoursePosition(), course.getCourseCredit()
                , course.getCourseHour(), course.getCourseDesc());
    }

    @Override
    public int updateCourse(Course course) {
        //language=sql
        String sql = " UPDATE `t_course` " +
                " SET `course_name` = ?, `course_type` = ?, `teacher_name` = ? " +
                " , `course_time` = ?, `course_position` = ? , `course_credit` = ? " +
                " , `course_hour` = ?, `course_desc` = ? " +
                " WHERE `course_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, course.getCourseName(), course.getCourseType(), course.getTeacherName()
                , course.getCourseTime(), course.getCoursePosition(), course.getCourseCredit()
                , course.getCourseHour(), course.getCourseDesc(), course.getCourseId());
    }

    @Override
    public int deleteCourse(Integer courseId) {
        //language=sql
        String sql = " UPDATE `t_course` " +
                " SET `removed` = 1 " +
                " WHERE `course_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, courseId);
    }

    @Override
    public Course getCourseByCourseId(Integer courseId) {
        //language=sql
        String sql = " SELECT `course_id`, `course_name`, `course_type`, `teacher_name` " +
                " , `course_time`, `course_position`, `course_credit`, `course_hour`" +
                " , `create_time`, `course_desc` " +
                " FROM `t_course` " +
                " WHERE `removed` = 0 " +
                " AND `course_id` = ? ";
        return getOne(Course.class, sql, courseId);
    }

    @Override
    public List<Course> getCourseByName(String courseName) {
        //language=sql
        String sql = " SELECT `course_id`, `course_name`, `course_type`, `teacher_name` " +
                " , `course_time`, `course_position`, `course_credit`, `course_hour`" +
                " , `create_time`, `course_desc` " +
                " FROM `t_course` " +
                " WHERE `removed` = 0 " +
                " AND `course_name` = LIKE '%' ? '%' ";
        return getList(Course.class, sql, courseName);
    }

    @Override
    public List<Course> getCourseList() {
        //language=sql
        String sql = " SELECT `course_id`, `course_name`, `course_type`, `teacher_name` " +
                " , `course_time`, `course_position`, `course_credit`, `course_hour`" +
                " , `create_time`, `course_desc` " +
                " FROM `t_course` " +
                " WHERE `removed` = 0 " +
                " ORDER BY `create_time` ASC ";
        return getList(Course.class, sql);
    }


}
