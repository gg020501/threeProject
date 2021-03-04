package com.fh.service;

import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentParams;

import java.util.Map;

public interface StudentService {

    public  void saveStudentData(Student student);

    Map  queryStudent (StudentParams params);

    void  updateStudentData(Student student);

    void  deleteStudentData(Integer id);
}
