package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.Class;
import com.zxd.www.service.ClassService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestBody;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.constant.RequestMethodConstant;

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

    @RequestMapping(path = "/class/all")
    public JsonResponse getClassList(){
        return new JsonResponse().data(classService.getAll());
    }

    /**
     * 获取指定年级和学院下的班级
     */
    @RequestMapping(path = "/class/cls/all")
    public JsonResponse getClasses(@RequestParam("instituteId")Integer instituteId, @RequestParam("gradeId") Integer gradeId){
        return new JsonResponse().data(classService.getAllClass(instituteId, gradeId));
    }

    @RequestMapping(path = "/class/cls/id")
    public JsonResponse getClassByClassId(@RequestParam("classId") Integer classId){
        return new JsonResponse().data(classService.getById(classId));
    }

    @RequestMapping(path = "/class/cls/delete", method = RequestMethodConstant.DELETE)
    public JsonResponse deleteClassById(@RequestParam("classId") Integer classId){
        if(classService.deleteClass(classId)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("删除班级信息失败！");
    }

    @RequestMapping(path = "/class/cls/update", method = RequestMethodConstant.PUT)
    public JsonResponse updateById(@RequestBody("class")Class clazz){
        if(classService.updateClass(clazz)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("更新班级信息失败!");
    }

    @RequestMapping(path = "/class/cls/add", method = RequestMethodConstant.POST)
    public JsonResponse addClass(@RequestBody("class") Class clazz){
        if(classService.addClass(clazz)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("添加班级信息失败！");
    }



}
