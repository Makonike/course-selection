package com.zxd.www.dao;

import com.zxd.www.po.*;
import com.zxd.www.po.Class;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-17 21:27
 **/
public interface ClassDao {

    int insertClass(Class clazz);

    int updateClass(Class clazz);

    int deleteClass(Integer classId);

    Class getClassByClassId(Integer classId);

    List<Class> getClassList();

    List<Institute> getInstituteList();

    List<Grade> getGradeList();

    List<Class> getClassList(Integer instituteId, Integer gradeId);
}
