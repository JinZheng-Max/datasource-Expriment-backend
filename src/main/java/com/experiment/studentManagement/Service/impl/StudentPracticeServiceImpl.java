package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.StudentPracticeInfoDTO;
import com.experiment.studentManagement.Service.StudentPracticeService;
import com.experiment.studentManagement.VO.StudentPracticeVO;
import com.experiment.studentManagement.mapper.StudentPracticeMapper;
import com.experiment.studentManagement.model.StudentPractice;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private StudentPracticeVO toVO(StudentPractice record) {
        StudentPracticeVO vo = new StudentPracticeVO();
        BeanUtils.copyProperties(record, vo);
        return vo;
    }
}
