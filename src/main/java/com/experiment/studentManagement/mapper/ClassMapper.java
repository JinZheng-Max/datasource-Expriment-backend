package com.experiment.studentManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {

    @Select("select class_id from class where class_name = #{className}")
    Integer findClassIdByName(String className);

    @Select("select class_name from class where major_id = #{majorId}")
    List<String> findAllClassName(Integer majorId);

    @Select("select class_name from class where class_id = #{classId}")
    String findClassNameById(Integer classId);
}
