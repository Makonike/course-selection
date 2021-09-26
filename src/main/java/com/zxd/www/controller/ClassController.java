package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.service.ClassService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;

/**
 * @author Makonike
 * @date 2021-09-26 19:07
 **/
@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 获取所有学院信息
     */
    @RequestMapping(path = "/class/ins/all")
    public JsonResponse getInstitute(){
        return new JsonResponse().data(classService.getAllInstitute());
    }

    /**
     * 获取所有年级信息
     */
    @RequestMapping(path = "/class/gra/all")
    public JsonResponse getGrade(){
        return new JsonResponse().data(classService.getAllGrade());
    }

    /**
     * 获取所有年级信息
     */
    @RequestMapping(path = "/class/cls/all")
    public JsonResponse getClasses(@RequestParam("instituteId")Integer instituteId, @RequestParam("gradeId") Integer gradeId){
        return new JsonResponse().data(classService.getAllClass(instituteId, gradeId));
    }

    @RequestMapping(path = "/class/cls/id")
    public JsonResponse getClassByClassId(@RequestParam("classId") Integer classId){
        return new JsonResponse().data(classService.getById(classId));
    }

}
