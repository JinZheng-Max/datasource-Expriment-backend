package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.StatusInfoDTO;
import com.experiment.studentManagement.DTO.StatusPageQueryDTO;
import com.experiment.studentManagement.Service.StudentStatusService;
import com.experiment.studentManagement.VO.StatusVO;
import com.experiment.studentManagement.exception.BizException;
import com.experiment.studentManagement.mapper.MajorMapper;
import com.experiment.studentManagement.mapper.StudentMapper;
import com.experiment.studentManagement.mapper.StudentStatusMapper;
import com.experiment.studentManagement.model.Student;
import com.experiment.studentManagement.model.StudentStatus;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentStatusServiceImpl implements StudentStatusService {

    @Autowired
    private StudentStatusMapper statusMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult pageQuery(StatusPageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("status", dto.getStatus());
        params.put("grade", dto.getGrade());
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = statusMapper.countByCondition(params);
        List<StudentStatus> list = statusMapper.pageByCondition(params);

        List<StatusVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }

    @Override
    public StatusVO getById(Integer statusId) {
        StudentStatus status = statusMapper.selectById(statusId);
        if (status == null)
            return null;
        return toVO(status);
    }

    @Override
    public StatusVO getByStudentId(Integer studentId) {
        StudentStatus status = statusMapper.selectByStudentId(studentId);
        if (status == null)
            return null;
        return toVO(status);
    }

    @Override
    public void updateStatus(StatusInfoDTO dto) {
        // 验证必填字段
        if (dto.getStatusDate() == null) {
            throw new IllegalArgumentException("状态变更日期不能为空");
        }

        StudentStatus status = new StudentStatus();
        BeanUtils.copyProperties(dto, status);

        statusMapper.updateById(status);
    }

    @Override
    public void addStatus(StatusInfoDTO dto) {
        if (dto.getStudentId() == null) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        if (dto.getStatusDate() == null) {
            throw new IllegalArgumentException("状态变更日期不能为空");
        }
        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            throw new IllegalArgumentException("学籍状态不能为空");
        }

        StudentStatus existed = statusMapper.selectByStudentId(dto.getStudentId());
        if (existed != null) {
            throw new BizException("该学生已存在学籍记录");
        }

        StudentStatus status = new StudentStatus();
        BeanUtils.copyProperties(dto, status);
        statusMapper.insert(status);
    }

    @Override
    public void deleteById(Integer statusId) {
        statusMapper.deleteById(statusId);
    }

    @Override
    public void initializeStatus() {
        // 查询没有学籍记录的学生ID
        List<Integer> studentIdsWithoutStatus = statusMapper.findStudentsWithoutStatus();

        if (studentIdsWithoutStatus.isEmpty()) {
            return;
        }

        // 为每个学生创建默认学籍记录
        List<StudentStatus> defaultStatusList = new ArrayList<>();
        for (Integer studentId : studentIdsWithoutStatus) {
            Student student = studentMapper.selectById(studentId);
            if (student != null) {
                StudentStatus status = new StudentStatus();
                status.setStudentId(studentId);
                status.setStatus("在读");
                status.setStatusDate(LocalDate.of(2025, 11, 12));
                status.setReason("初始状态");
                defaultStatusList.add(status);
            }
        }

        // 批量插入
        if (!defaultStatusList.isEmpty()) {
            statusMapper.batchInsertDefaultStatus(defaultStatusList);
        }
    }

    private StatusVO toVO(StudentStatus status) {
        StatusVO vo = new StatusVO();
        BeanUtils.copyProperties(status, vo);
        return vo;
    }
}
