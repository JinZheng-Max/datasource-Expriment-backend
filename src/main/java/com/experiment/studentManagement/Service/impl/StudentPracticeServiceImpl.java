package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.PracticeApplyDTO;
import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.ReviewDTO;
import com.experiment.studentManagement.DTO.StudentPracticeInfoDTO;
import com.experiment.studentManagement.Service.StudentPracticeService;
import com.experiment.studentManagement.VO.StudentPracticeVO;
import com.experiment.studentManagement.mapper.StudentPracticeMapper;
import com.experiment.studentManagement.model.StudentPractice;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentPracticeServiceImpl implements StudentPracticeService {

    @Autowired
    private StudentPracticeMapper recordMapper;

    @Override
    public void addRecord(StudentPracticeInfoDTO dto) {
        StudentPractice record = new StudentPractice();
        BeanUtils.copyProperties(dto, record);
        recordMapper.insert(record);
    }

    @Override
    public PageResult pageQuery(PracticePageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("practiceType", dto.getPracticeType());
        params.put("status", dto.getStatus());
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = recordMapper.countByCondition(params);
        List<StudentPractice> list = recordMapper.pageByCondition(params);

        List<StudentPracticeVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }

    @Override
    public StudentPracticeVO getById(Integer recordId) {
        StudentPractice record = recordMapper.selectById(recordId);
        if (record == null)
            return null;
        return toVO(record);
    }

    @Override
    public void updateRecord(StudentPracticeInfoDTO dto) {
        StudentPractice record = new StudentPractice();
        BeanUtils.copyProperties(dto, record);
        recordMapper.updateById(record);
    }

    @Override
    public void deleteById(Integer recordId) {
        recordMapper.deleteById(recordId);
    }

    @Override
    public List<StudentPracticeVO> getAllRecords() {
        List<StudentPractice> list = recordMapper.findAll();
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public List<StudentPracticeVO> getByStudentId(Integer studentId) {
        List<StudentPractice> list = recordMapper.findByStudentId(studentId);
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    private StudentPracticeVO toVO(StudentPractice record) {
        StudentPracticeVO vo = new StudentPracticeVO();
        BeanUtils.copyProperties(record, vo);
        return vo;
    }

    @Override
    public void apply(Integer studentId, PracticeApplyDTO dto) {
        StudentPractice record = new StudentPractice();
        record.setStudentId(studentId);
        record.setPracticeId(dto.getPracticeId());
        record.setRole(dto.getRole());
        record.setDuration(dto.getDuration());
        record.setCertificateUrl(dto.getCertificateUrl());
        record.setApplyRemark(dto.getApplyRemark());
        record.setApplyTime(LocalDateTime.now());
        record.setStatus("待审核");
        recordMapper.insert(record);
    }

    @Override
    public void review(Integer reviewerId, ReviewDTO dto) {
        StudentPractice record = recordMapper.selectById(dto.getId());
        if (record == null) {
            throw new RuntimeException("记录不存在");
        }
        if (!"待审核".equals(record.getStatus())) {
            throw new RuntimeException("该记录已审核");
        }
        record.setStatus(dto.getStatus());
        record.setReviewerId(reviewerId);
        record.setReviewTime(LocalDateTime.now());
        record.setReviewComment(dto.getReviewComment());
        if (dto.getPerformanceScore() != null) {
            record.setPerformanceScore(dto.getPerformanceScore());
        }
        if (dto.getEvaluation() != null) {
            record.setEvaluation(dto.getEvaluation());
        }
        recordMapper.updateById(record);
    }

    @Override
    public PageResult getPendingList(PracticePageQueryDTO dto) {
        dto.setStatus("待审核");
        return pageQuery(dto);
    }
}
