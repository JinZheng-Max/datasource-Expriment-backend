package com.experiment.studentManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MajorMapper {

    // 查询所有的专业名称
    @Select("select major_name from major")
    List<String> findAll();

    // 根据专业名称查询专业id
    @Select("select major_id from major where major_name = #{major}")
    Integer findIdByName(String major);

    // 根据专业id查询专业名称
    @Select("select major_name from major where major_id = #{majorId}")
    String findMajorNameById(Integer majorId);

    /**
     * 查询所有专业(包含ID和名称)
     */
    @Select("select major_id as majorId, major_name as majorName from major order by major_id")
    List<Map<String, Object>> findAllWithId();
}
