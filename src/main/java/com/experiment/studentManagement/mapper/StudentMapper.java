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

    @Select("SELECT s.student_id, s.student_no, s.name, s.grade, m.major_name as major " +
            "FROM student s " +
            "LEFT JOIN major m ON s.major_id = m.major_id " +
            "ORDER BY s.student_no")
    List<Student> findAll();
}
