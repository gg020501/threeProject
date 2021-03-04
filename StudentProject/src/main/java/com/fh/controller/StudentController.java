package com.fh.controller;

import com.fh.entity.po.Student;
import com.fh.entity.vo.ResultData;
import com.fh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/student")
public class StudentController {


    @Autowired
    private StudentService studentService;


    /*
     * 新增学生信息的接口文档
     * 路径  http://localhost:8003/api/student/add
     *
     * 请求方式  post
     *
     * 参数   sex // 0 女 1 男     hobby //  爱好 1 吃  2 喝  3 玩  4 乐
     *
     * 返回值 回值    {code:"",message:"",data:null}
     * */
    @PostMapping("add")
    public ResultData saveStudentData(Student student) {
        studentService.saveStudentData(student);
        return ResultData.success("");
    }

}
