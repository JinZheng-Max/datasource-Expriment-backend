package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.PracticeInfoDTO;
import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.VO.PracticeVO;
import com.experiment.studentManagement.result.PageResult;

public interface PracticeService {
    /**
     * 添加实践记录
     */
    void addPractice(PracticeInfoDTO dto);

    /**
     * 分页查询实践记录
     */
    PageResult pageQuery(PracticePageQueryDTO dto);

    /**
     * 根据recordId查询详情
     */
    PracticeVO getById(Integer recordId);

    /**
     * 更新实践记录
     */
    void updatePractice(PracticeInfoDTO dto);

    /**
     * 删除实践记录
     */
    void deleteById(Integer recordId);
}
