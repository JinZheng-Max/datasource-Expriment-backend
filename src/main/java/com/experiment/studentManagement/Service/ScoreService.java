package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.ScoreInfoDTO;
import com.experiment.studentManagement.DTO.ScorePageQueryDTO;
import com.experiment.studentManagement.VO.ScoreVO;
import com.experiment.studentManagement.result.PageResult;

import java.util.List;

public interface ScoreService {
    void addScore(ScoreInfoDTO dto);

    PageResult pageQuery(ScorePageQueryDTO dto);

    ScoreVO getById(Integer scoreId);

    void updateScore(ScoreInfoDTO dto);

    void deleteById(Integer scoreId);

    List<ScoreVO> getAllScores();
}