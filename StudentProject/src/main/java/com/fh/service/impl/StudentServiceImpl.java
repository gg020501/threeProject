package com.fh.service.impl;

import com.fh.dao.StudentDao;
import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentParams;
import com.fh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl  implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public void saveStudentData(Student student) {
        studentDao.saveStudentData(student);
    }

    @Override
    public Map queryStudent(StudentParams params) {

        Map map = new HashMap();

        // 学生的总条数
        Long count = studentDao.queryStudnetCount(params);
        // 学生的数据
        List<Student> studentList = studentDao.queryStudentData(params);

        map.put("count",count);
        map.put("data",studentList);

        return map;
    }

    @Override
    public void updateStudentData(Student student) {
        studentDao.updateStudentData(student);
    }

    @Override
    public void deleteStudentData(Integer id) {
        studentDao.deleteStudentData(id);
    }
}
