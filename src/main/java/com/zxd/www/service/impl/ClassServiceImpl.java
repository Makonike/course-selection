package com.zxd.www.service.impl;

import com.zxd.www.dao.ClassDao;
import com.zxd.www.po.Class;
import com.zxd.www.service.ClassService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.ioc.annotation.Component;

import java.util.List;

/**
 * 班级service实现类
 * @author Makonike
 * @date 2021-09-19 16:13
 **/
@Component
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassDao classDao;

    @Override
    public int addClass(Class clazz) {
        return classDao.insertClass(clazz);
    }

    @Override
    public int updateClass(Class clazz) {
        return classDao.updateClass(clazz);
    }

    @Override
    public int deleteClass(Integer classId) {
        return classDao.deleteClass(classId);
    }

    @Override
    public Class getById(Integer classId) {
        return classDao.getClassByClassId(classId);
    }

    @Override
    public List<Class> getAll() {
        return classDao.getClassList();
    }
}
