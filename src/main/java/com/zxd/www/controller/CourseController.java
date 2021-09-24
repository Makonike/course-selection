package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.Course;
import com.zxd.www.service.CourseService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-24 10:56
 **/
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(path = "/course/getAll")
    public JsonResponse getAll(){
        List<Course> courses = courseService.getAll();
        return new JsonResponse().data(courses);
    }

    /**
     * 模糊搜索
     * @param courseName 课程名
     */
    @RequestMapping(path = "/course/name")
    public JsonResponse getByName(@RequestParam("courseName") String courseName){
        List<Course> courses = courseService.getByName(courseName);
        return new JsonResponse().data(courses);
    }

    @RequestMapping(path = "/course/id")
    public JsonResponse getById(@RequestParam("courseId") String courseIdString){
        Integer courseId = Integer.valueOf(courseIdString);
        Course course = courseService.getById(courseId);
        if(course != null){
            return new JsonResponse().data(course);
        }
        return new JsonResponse().notFound().message("未找到该课程");
    }





}
