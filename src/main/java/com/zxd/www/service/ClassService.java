package com.zxd.www.service;

import com.zxd.www.po.Class;
import com.zxd.www.po.Grade;
import com.zxd.www.po.Institute;

import java.util.List;

/**
 * 班级service接口
 * @author Makonike
 * @date 2021-09-19 16:12
 **/
public interface ClassService {

    boolean addClass(Class clazz);

    boolean updateClass(Class clazz);

    boolean deleteClass(Integer classId);

    Class getById(Integer classId);

    List<Class> getAll();

    List<Institute> getAllInstitute();

    List<Grade> getAllGrade();

    List<Class> getAllClass(Integer instituteId, Integer gradeId);

}
