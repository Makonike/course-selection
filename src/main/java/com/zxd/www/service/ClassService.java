package com.zxd.www.service;

import com.zxd.www.po.Class;

import java.util.List;

/**
 * 班级service接口
 * @author Makonike
 * @date 2021-09-19 16:12
 **/
public interface ClassService {

    int addClass(Class clazz);

    int updateClass(Class clazz);

    int deleteClass(Integer classId);

    Class getById(Integer classId);

    List<Class> getAll();
}
