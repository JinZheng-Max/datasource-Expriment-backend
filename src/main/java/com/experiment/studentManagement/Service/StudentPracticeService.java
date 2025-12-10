package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.StudentPracticeInfoDTO;
import com.experiment.studentManagement.VO.StudentPracticeVO;
import com.experiment.studentManagement.result.PageResult;

import java.util.List;

public interface StudentPracticeService {
    void addRecord(StudentPracticeInfoDTO dto);

    PageResult pageQuery(PracticePageQueryDTO dto);

    StudentPracticeVO getById(Integer recordId);

    void updateRecord(StudentPracticeInfoDTO dto);

    void deleteById(Integer recordId);

    List<StudentPracticeVO> getAllRecords();
}
