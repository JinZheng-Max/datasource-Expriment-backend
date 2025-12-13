package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.RewardPunishmentInfoDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentPageQueryDTO;
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
}
