package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.PracticeApplyDTO;
import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.ReviewDTO;
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

    List<StudentPracticeVO> getByStudentId(Integer studentId);

    /**
     * 学生申请社会实践
     */
    void apply(Integer studentId, PracticeApplyDTO dto);

    /**
     * 管理员审核社会实践申请
     */
    void review(Integer reviewerId, ReviewDTO dto);

    /**
     * 获取待审核列表
     */
    PageResult getPendingList(PracticePageQueryDTO dto);
}
