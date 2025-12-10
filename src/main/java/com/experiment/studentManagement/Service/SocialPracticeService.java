package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.SocialPracticeInfoDTO;
import com.experiment.studentManagement.VO.SocialPracticeVO;
import com.experiment.studentManagement.result.PageResult;

import java.util.List;

public interface SocialPracticeService {
    void addPractice(SocialPracticeInfoDTO dto);

    PageResult pageQuery(PracticePageQueryDTO dto);

    SocialPracticeVO getById(Integer practiceId);

    void updatePractice(SocialPracticeInfoDTO dto);

    void deleteById(Integer practiceId);

    List<SocialPracticeVO> getAllPractices();
}
