package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface CourseMapper {
    void insert(Course course);

    Course selectById(@Param("courseId") Integer courseId);

    void updateById(Course course);

    void deleteById(@Param("courseId") Integer courseId);

    long countByCondition(Map<String, Object> params);

    List<Course> pageByCondition(Map<String, Object> params);

    List<Course> findAll();
}
