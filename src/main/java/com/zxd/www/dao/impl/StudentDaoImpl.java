package com.zxd.www.dao.impl;

import com.zxd.www.dao.BaseDao;
import com.zxd.www.dao.StudentDao;
import com.zxd.www.util.ioc.annotation.Component;
import com.zxd.www.po.Student;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-12 23:17
 **/
@Component
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public int insertStudent(Student student) {
        //language=sql
        String sql = " INSERT INTO `t_student` " +
                " (`user_id`, `student_no`, `student_name`, `student_sex` , `student_class_id`)" +
                " VALUES(?, ?, ?, ?, ?)";
        return update(sql, student.getUserId(), student.getStudentNo(), student.getStudentName(), student.getStudentSex()
        , student.getStudentClassId());
    }

    @Override
    public int updateStudent(Student student) {
        //language=sql
        String sql = " UPDATE `t_student` " +
                " SET `student_no` = ?, `student_name` = ?, `student_sex` = ? " +
                " , `student_class_id` = ?, `selected_course_count` = ? " +
                " WHERE `student_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, student.getStudentNo(), student.getStudentName(), student.getStudentSex()
        , student.getStudentClassId(), student.getSelectedCourseCount(), student.getStudentId());
    }

    /**
     * 用update做伪删除
     * @param studentId 学生id
     * @return
     */
    @Override
    public int deleteStudent(Integer studentId) {
        //language=sql
        String sql = " UPDATE `t_student` " +
                " SET `removed` = 1 " +
                " WHERE `student_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, studentId);
    }

    @Override
    public Student getStudentByNo(String studentNo) {
        //language=sql
        String sql = " SELECT `student_id`, `student_no`, `student_name`, `student_sex` " +
                " , `student_class_id`, `selected_course_count` " +
                " FROM `t_student` " +
                " WHERE `removed` = 0 " +
                " AND `student_no` = ? ";
        return getOne(Student.class, sql, studentNo);
    }

    @Override
    public List<Student> getStudentByName(String studentName) {
        //language=sql
        String sql = " SELECT `student_id`, `student_no`, `student_name`, `student_sex` " +
                " , `student_class_id`, `selected_course_count` " +
                " FROM `t_student` " +
                " WHERE `removed` = 0 " +
                " AND `student_name` LIKE '%' ? '%' ";
        return getList(Student.class, sql, studentName);
    }

    @Override
    public List<Student> getStudentList() {
        //language=sql
        String sql = " SELECT `student_id`, `student_no`, `student_name`, `student_sex` " +
                " , `student_class_id`, `selected_course_count` " +
                " FROM `t_student` " +
                " WHERE `removed` = 0 " +
                " ORDER BY `student_id` ASC ";
        return getList(Student.class, sql);
    }
}
