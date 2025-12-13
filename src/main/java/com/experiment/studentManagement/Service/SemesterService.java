package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.SemesterDeleteDTO;
import com.experiment.studentManagement.DTO.SemesterInfoDTO;
import com.experiment.studentManagement.VO.SemesterVO;

import java.util.List;

public interface SemesterService {
    void addSemester(SemesterInfoDTO dto);

    SemesterVO getById(Integer semesterId);

    void updateSemester(SemesterInfoDTO dto);

    void deleteById(Integer semesterId);

    List<SemesterVO> getAllSemesters();

    SemesterVO getCurrentSemester();

    // 根据学年和学期序号删除学期及其关联数据
    void deleteSemesterByYearAndOrder(SemesterDeleteDTO dto);

    // 获取所有学年列表
    List<String> getAllAcademicYears();
}
