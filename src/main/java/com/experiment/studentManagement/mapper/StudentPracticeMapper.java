package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.StudentPractice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentPracticeMapper {
    void insert(StudentPractice record);

    StudentPractice selectById(@Param("recordId") Integer recordId);

    void updateById(StudentPractice record);

    void deleteById(@Param("recordId") Integer recordId);

    long countByCondition(Map<String, Object> params);

    List<StudentPractice> pageByCondition(Map<String, Object> params);

    List<StudentPractice> findAll();
}
