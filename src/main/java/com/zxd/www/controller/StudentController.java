package com.zxd.www.controller;

import com.zxd.www.global.po.JsonResponse;
import com.zxd.www.service.StudentService;
import com.zxd.www.util.ioc.annotation.Autowired;
import com.zxd.www.util.mvc.annotation.Controller;
import com.zxd.www.util.mvc.annotation.RequestMapping;

/**
 * @author Makonike
 * @date 2021-09-20 16:43
 **/
@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(path = "/student/getAll")
    public JsonResponse getAll(){
        return new JsonResponse().data(studentService.getStudentList());
    }

}
