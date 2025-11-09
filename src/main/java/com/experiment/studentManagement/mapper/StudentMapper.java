package com.experiment.studentManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
}
