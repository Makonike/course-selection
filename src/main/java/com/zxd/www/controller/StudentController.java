package com.zxd.www.controller;

import com.zxd.www.global.dto.JsonResponse;
import com.zxd.www.po.Student;
import com.zxd.www.service.StudentService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestBody;
import com.zxd.www.util.mvc.annotation.RequestMapping;
import com.zxd.www.util.mvc.annotation.RequestParam;
import com.zxd.www.util.mvc.constant.RequestMethodConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Makonike
 * @date 2021-09-20 16:43
 **/
@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 获取所有学生信息
     */
    @RequestMapping(path = "/student/all")
    public JsonResponse getAll(){
        return new JsonResponse().data(studentService.getStudentList());
    }

    /**
     * 根据学号查找学生
     * @param studentNo 学号
     */
    @RequestMapping(path = "/student/no")
    public JsonResponse findByStudentNo(@RequestParam("studentNo") String studentNo){
        return new JsonResponse().data(studentService.getStudentByNo(studentNo));
    }

    /**
     * 学生姓名模糊查询
     * @param studentName 学生姓名
     */
    @RequestMapping(path = "/student/name")
    public JsonResponse findByStudentName(@RequestParam("studentName") String studentName) {
        return new JsonResponse().data(studentService.getStudentByName(studentName));
    }

    /**
     * 删除学生信息
     */
    @RequestMapping(path = "/student",method = RequestMethodConstant.DELETE)
    public JsonResponse deleteById(@RequestParam("studentId") Integer studentId) {
        if(studentService.deleteStudent(studentId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("studentId错误，未找到该学生");
    }

    /**
     * 添加学生信息，并自动绑定当前用户
     * @param student 学生信息
     * @param request 请求
     */
    @RequestMapping(path = "/student/add", method = RequestMethodConstant.POST)
    public JsonResponse addStudent(@RequestBody("student")Student student, HttpServletRequest request){
        student.setUserId((Integer) request.getAttribute("userId"));
        if(studentService.addStudent(student)){
            Student byNo = studentService.getStudentByNo(student.getStudentNo());
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("请求错误，添加信息失败！");
    }

    /**
     * 更新学生信息
     * @param student 学生信息
     */
    @RequestMapping(path = "/student/update", method = RequestMethodConstant.PUT)
    public JsonResponse updateStudent(@RequestBody("student") Student student){
        boolean b = studentService.updateStudent(student);
        if(b){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("请求错误，更新信息失败！");
    }

    /**
     * 获取当前登录的用户的学生信息
     * @param request 请求
     */
    @RequestMapping(path = "/student/id")
    public JsonResponse getById(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");
        Student student = studentService.getStudentByUserId(userId);
        if(student.getStudentId() != null){
            return new JsonResponse().data(student);
        }
        return new JsonResponse().notFound().message("该用户还没有创建学生信息！");
    }

    /**
     * 学生选课
     * @param request 请求
     */
    @RequestMapping(path = "/student/select", method = RequestMethodConstant.POST)
    public JsonResponse selectCourse(@RequestParam("courseId") Integer courseId, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");
        boolean b = studentService.addCourse(userId, courseId);
        if(b){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("选课失败！");
    }

}
