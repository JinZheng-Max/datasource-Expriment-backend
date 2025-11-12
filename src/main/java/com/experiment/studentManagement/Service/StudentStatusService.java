package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.StatusInfoDTO;
import com.experiment.studentManagement.DTO.StatusPageQueryDTO;
import com.experiment.studentManagement.VO.StatusVO;
import com.experiment.studentManagement.result.PageResult;

public interface StudentStatusService {
    void addStatus(StatusInfoDTO dto);

    PageResult pageQuery(StatusPageQueryDTO dto);

    StatusVO getById(Integer statusId);

    void updateStatus(StatusInfoDTO dto);

    void deleteById(Integer statusId);

    /**
     * 初始化学籍信息 - 为没有学籍记录的学生创建默认记录
     */
    void initializeStatus();
}
