package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface ScoreMapper {
    void insert(Score score);

    Score selectById(@Param("scoreId") Integer scoreId);

    void updateById(Score score);

    void deleteById(@Param("scoreId") Integer scoreId);

    long countByCondition(Map<String, Object> params);

    List<Score> pageByCondition(Map<String, Object> params);

    List<Score> findAll();

    List<Score> findByStudentId(@Param("studentId") Integer studentId);
}
