package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.Course;
import com.zxd.www.po.CourseDel;
import com.zxd.www.service.CourseService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestBody;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.constant.RequestMethodConstant;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-24 10:56
 **/
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取所有课程列表
     */
    @RequestMapping(path = "/course/getCourseAll")
    public JsonResponse getCourseAll(){
        List<Course> courses = courseService.getCourseAll();
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

    /**
     * 添加课程
     * @param course 课程信息
     */
    @RequestMapping(path = "/course/add", method = RequestMethodConstant.POST)
    public JsonResponse addCourse(@RequestBody("course") Course course){
        if(courseService.addCourse(course)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("请求错误，添加课程信息失败！");
    }

    /**
     * 更新课程
     * @param course 课程信息
     */
    @RequestMapping(path = "/course/update", method = RequestMethodConstant.PUT)
    public JsonResponse updateCourse(@RequestBody("course") Course course){
        if(courseService.updateCourse(course)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("请求错误，修改课程信息失败！");

    }

    /**
     * 删除课程
     * @param courseId 课程号
     */
    @RequestMapping(path = "/course/delete", method = RequestMethodConstant.DELETE)
    public JsonResponse deleteCourse(@RequestParam("courseId") Integer courseId){
        if(courseService.deleteCourse(courseId)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("请求错误，删除课程信息失败！");

    }

    /**
     * 获取所有选课信息
     */
    @RequestMapping(path = "/course/del/getAll")
    public JsonResponse getCourseDelAll(){
        return new JsonResponse().data(courseService.getCourseDelAll());
    }

    /**
     * 查看课程详情
     * @param courseId 课程号
     */
    @RequestMapping(path = "/course/check")
    public JsonResponse checkCourseDel(@RequestParam("courseId") Integer courseId){
        return new JsonResponse().data(courseService.getById(courseId));
    }

    /**
     * 删除选课信息
     * @param courseDelId 选课id
     */
    @RequestMapping(path = "/course/del/delete", method = RequestMethodConstant.DELETE)
    public JsonResponse deleteCourseDel(@RequestParam("courseDelId") Integer courseDelId){
        if(courseService.deleteCourseDel(courseDelId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除选课失败！");
    }

    /**
     * 根据选课id获取选课信息
     * @param courseDelId 选课id
     */
    @RequestMapping(path = "/course/del/id")
    public JsonResponse getCourseDelById(@RequestParam("courseDelId") Integer courseDelId){
        return new JsonResponse().data(courseService.getCourseDelById(courseDelId));
    }

    /**
     * 更新选课信息
     * @param courseDel 选课id
     */
    @RequestMapping(path = "/course/del/update", method = RequestMethodConstant.PUT)
    public JsonResponse updateCourseDel(@RequestBody("courseDel")CourseDel courseDel){
        if(courseService.updateCourseDel(courseDel)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("更新选课信息失败");
    }

    @RequestMapping(path = "/course/del/add", method = RequestMethodConstant.POST)
    public JsonResponse addCourseDel(@RequestBody("courseDel") CourseDel courseDel){
        System.out.println(courseDel);
        if(courseService.addCourseDel(courseDel)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("新增选课信息失败");
    }

}
