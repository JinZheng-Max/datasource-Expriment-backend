package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.SocialPractice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface SocialPracticeMapper {
    void insert(SocialPractice practice);

    SocialPractice selectById(@Param("practiceId") Integer practiceId);

    void updateById(SocialPractice practice);

    void deleteById(@Param("practiceId") Integer practiceId);

    long countByCondition(Map<String, Object> params);

    List<SocialPractice> pageByCondition(Map<String, Object> params);

    List<SocialPractice> findAll();
}
