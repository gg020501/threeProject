package com.fh.controller;

import com.fh.entity.po.Student;
import com.fh.entity.vo.ResultData;
import com.fh.entity.vo.StudentParams;
import com.fh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/student")
@CrossOrigin
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

     /*
     * 查询学生信息的分页接口文档
     * 路径  http://localhost:8003/api/student/select
     *
     * 请求方式  post
     *
     * 参数   start size 必传
     *
     * 返回值 回值    {code:"",message:"",data:分页数据}
     * */
    @PostMapping("select")
    public  ResultData  selectStudentData(StudentParams params){
        if (params.getStart()==null){
            return  ResultData.error(400,"参数不符合规则");
        }
        if (params.getSize()==null){
            return  ResultData.error(400,"参数不符合规则");
        }
        Map map = studentService.queryStudent(params);
        return  ResultData.success(map);
    }

    /*
     * 修改学生信息的接口文档
     * 路径  http://localhost:8003/api/student/update
     *
     * 请求方式  post
     *
     * 参数   sex // 0 女 1 男     hobby //  爱好 1 吃  2 喝  3 玩  4 乐
     *
     * 返回值 回值    {code:"",message:"",data:null}
     * */
    @PostMapping("update")
    public  ResultData  updateStudentData(Student student){
        studentService.updateStudentData(student);
        return  ResultData.success("");
    }

    /*
     * 删除学生信息的接口文档
     * 路径  http://localhost:8003/api/student/delete
     *
     * 请求方式  post
     *
     * 参数   id 必传
     *
     * 返回值 回值    {code:"",message:"",data:null}
     * */
    @PostMapping("delete")
    public  ResultData  deleteStudentData(Integer id){
        if (id==null){
            return  ResultData.error(400,"参数不符合规则");
        }
        studentService.deleteStudentData(id);
        return  ResultData.success("");
    }

}
