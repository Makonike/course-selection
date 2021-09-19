package com.zxd.www.dao.impl;

import com.zxd.www.dao.BaseDao;
import com.zxd.www.dao.ClassDao;
import com.zxd.www.po.Class;
import com.zxd.www.po.Student;
import com.zxd.www.util.ioc.annotation.Component;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-17 21:31
 **/
@Component
public class ClassDaoImpl extends BaseDao implements ClassDao {

    @Override
    public int insertClass(Class clazz) {
        //language=sql
        String sql = " INSERT INTO `t_class` " +
                " (`class_Name`, `institute_id`, `grade_id` , `createTime`)" +
                " VALUES(?, ?, ?, ?) ";
        return update(sql, clazz.getClassName(), clazz.getInstituteId(), clazz.getGradeId()
                , clazz.getCreateTime());
    }

    @Override
    public int updateClass(Class clazz) {
        //language=sql
        String sql = " UPDATE `t_class` " +
                "  SET `class_Name` = ?, `institute_id` = ?, `grade_id` = ? " +
                " WHERE `class_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, clazz.getClassName(), clazz.getInstituteId(), clazz.getGradeId()
                , clazz.getClassId());
    }

    @Override
    public int deleteClass(Integer classId) {
        //language=sql
        String sql = " UPDATE `t_class` " +
                " SET `removed` = 1 " +
                " WHERE `class_id` = ? " +
                " AND `removed` = 0 ";
        return update(sql, classId);
    }

    @Override
    public Class getClassByClassId(Integer classId) {
        //language=sql
        String sql = " SELECT `class_id`, `class_name`, `institute_id`, `grade_id` " +
                " , `create_time` " +
                " FROM `t_class` " +
                " WHERE `removed` = 0 " +
                " AND `class_id` = ? ";
        return getOne(Class.class, sql, classId);
    }

    @Override
    public List<Class> getClassList() {
        //language=sql
        String sql = " SELECT `class_id`, `class_name`, `institute_id`, `grade_id` " +
                " , `create_time` " +
                " FROM `t_class` " +
                " WHERE `removed` = 0 " +
                " ORDER BY `create_time` ASC ";
        return getList(Class.class, sql);
    }
}
