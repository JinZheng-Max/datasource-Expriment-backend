package com.experiment.studentManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.experiment.studentManagement.model.Student;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    void insert(Student student);

    Student selectById(@Param("studentId") Integer studentId);

    void updateById(Student student);

    void deleteById(@Param("studentId") Integer studentId);

    long countByCondition(Map<String, Object> params);

    List<Student> pageByCondition(Map<String, Object> params);

    @Select("select student_id, student_no, name from student order by student_no")
    List<Student> findAll();
}
