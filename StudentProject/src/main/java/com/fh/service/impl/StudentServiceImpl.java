package com.fh.service.impl;

import com.fh.dao.StudentDao;
import com.fh.entity.po.Student;
import com.fh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class StudentServiceImpl  implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public void saveStudentData(Student student) {
        studentDao.saveStudentData(student);
    }
}
