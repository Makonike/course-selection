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

    @RequestMapping(path = "/student/all")
    public JsonResponse getAll(){
        return new JsonResponse().data(studentService.getStudentList());
    }

    @RequestMapping(path = "/student/no")
    public JsonResponse findByStudentNo(@RequestParam("studentNo") String studentNo){
        return new JsonResponse().data(studentService.getStudentByNo(studentNo));
    }

    @RequestMapping(path = "/student/name")
    public JsonResponse findByStudentName(@RequestParam("studentName") String studentName) {
        return new JsonResponse().data(studentService.getStudentByName(studentName));
    }

    @RequestMapping(path = "/student",method = RequestMethodConstant.DELETE)
    public JsonResponse deleteById(@RequestParam("studentId") Integer studentId) {
        if(studentService.deleteStudent(studentId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("studentId错误，未找到该学生");
    }

    @RequestMapping(path = "/student/add", method = RequestMethodConstant.POST)
    public JsonResponse addStudent(@RequestBody("student")Student student, HttpServletRequest request){
        student.setUserId((Integer) request.getAttribute("userId"));
        if(studentService.addStudent(student)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("服务器错误");
    }

}
