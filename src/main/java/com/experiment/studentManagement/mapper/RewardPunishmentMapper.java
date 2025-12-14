package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.RewardPunishment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface RewardPunishmentMapper {
    void insert(RewardPunishment rp);

    RewardPunishment selectById(@Param("rpId") Integer rpId);

    void updateById(RewardPunishment rp);

    void deleteById(@Param("rpId") Integer rpId);

    long countByCondition(Map<String, Object> params);

    List<RewardPunishment> pageByCondition(Map<String, Object> params);

    List<RewardPunishment> findAll();

    List<RewardPunishment> findByStudentId(@Param("studentId") Integer studentId);
}
