package com.fh.dao;

import com.fh.entity.po.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao {

    @Insert("insert into student_fen (name,sex,birthday,score,hobby) values(#{name},#{sex},#{birthday},#{score},#{hobby})")
    public  void saveStudentData(Student student);

}
