package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.RewardApplyDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentInfoDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentPageQueryDTO;
import com.experiment.studentManagement.DTO.ReviewDTO;
import com.experiment.studentManagement.VO.RewardPunishmentVO;
import com.experiment.studentManagement.result.PageResult;

import java.util.List;

public interface RewardPunishmentService {
    void add(RewardPunishmentInfoDTO dto);

    PageResult pageQuery(RewardPunishmentPageQueryDTO dto);

    RewardPunishmentVO getById(Integer rpId);

    void update(RewardPunishmentInfoDTO dto);

    void deleteById(Integer rpId);

    List<RewardPunishmentVO> getAll();

    List<RewardPunishmentVO> getByStudentId(Integer studentId);

    /**
     * 学生申请奖励
     */
    void apply(Integer studentId, RewardApplyDTO dto);

    /**
     * 管理员审核奖励申请
     */
    void review(Integer reviewerId, ReviewDTO dto);

    /**
     * 获取待审核列表
     */
    PageResult getPendingList(RewardPunishmentPageQueryDTO dto);
}
