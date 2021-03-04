package com.fh.dao;

import com.fh.entity.po.Student;
import com.fh.entity.vo.StudentParams;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentDao {

    @Insert("insert into student_fen (name,sex,birthday,score,hobby) values(#{name},#{sex},#{birthday},#{score},#{hobby})")
    public  void saveStudentData(Student student);


    public  Long  queryStudnetCount(StudentParams params);

    public List<Student> queryStudentData(StudentParams params);

    @Update("update student_fen set name=#{name},sex=#{sex},birthday=#{birthday},score=#{score},hobby=#{hobby} where id=#{id}")
    public  void updateStudentData(Student student);


    @Delete("delete from student_fen where id=#{id}")
    public  void  deleteStudentData(Integer id);

}
