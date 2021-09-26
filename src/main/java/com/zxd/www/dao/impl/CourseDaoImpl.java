package com.zxd.www.dao.impl;

import com.zxd.www.dao.BaseDao;
import com.zxd.www.dao.CourseDao;
import com.zxd.www.po.Class;
import com.zxd.www.po.Course;
import com.zxd.www.po.CourseDel;
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

    @Override
    public List<CourseDel> getCourseDelList(){
        //language=sql
        String sql = " SELECT `coursedel_id`, `course_id`, `course_name`, `start_time` " +
                " , `exp_time`, `max_num`, `now_num`, `coursedel_desc`, `status`" +
                " FROM `t_coursedel` " +
                " ORDER BY `start_time` ASC ";
        return getList(CourseDel.class, sql);
    }

    @Override
    public CourseDel getCourseDelById(Integer courseDelId){
        //language=sql
        String sql = " SELECT `coursedel_id`, `course_id`, `course_name`, `start_time` " +
                " , `exp_time`, `max_num`, `now_num`, `coursedel_desc`, `status`" +
                " FROM `t_coursedel` " +
                " WHERE `coursedel_id` = ? " +
                " ORDER BY `start_time` ASC ";
        return getOne(CourseDel.class, sql, courseDelId);
    }

    @Override
    public int expCourseDel(Integer courseDelId){
        //language=sql
        String sql = " UPDATE `t_coursedel` " +
                " SET `status` = 1 " +
                " WHERE `coursedel_id` = ? " +
                " AND `status` = 0 ";
        return update(sql, courseDelId);
    }


    @Override
    public int incrCourseDelNum(Integer courseDelId){
        //language=sql
        String sql = " UPDATE `t_coursedel` " +
                " SET `now_num` = `now_num` + 1 " +
                " WHERE `status` = 0 " +
                " AND  `coursedel_id` = ? ";
        return update(sql, courseDelId);
    }

    @Override
    public int decrCourseDelNum(Integer courseDelId){
        //language=sql
        String sql = " UPDATE `t_coursedel` " +
                " SET `now_num` = `now_num` - 1 " +
                " WHERE `status` = 0 " +
                " AND  `coursedel_id` = ? ";
        return update(sql, courseDelId);
    }

    @Override
    public List<Integer> getAllCourseByStudentId(Integer studentId){
        //language=sql
        String sql = " SELECT `course_id` " +
                " FROM `conn_student_course` " +
                " WHERE `student_id` = ? ";
        return getValue(Integer.class, sql, studentId);

    }


}
