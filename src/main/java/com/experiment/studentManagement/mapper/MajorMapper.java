package com.experiment.studentManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajorMapper {

    //查询所有的专业名称
    @Select("select major_name from major")
    List<String> findAll();

    //根据专业名称查询专业id
    @Select("select major_id from major where major_name = #{major}")
    Integer findIdByName(String major);
}
