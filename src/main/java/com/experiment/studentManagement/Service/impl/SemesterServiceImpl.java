package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.SemesterDeleteDTO;
import com.experiment.studentManagement.DTO.SemesterInfoDTO;
import com.experiment.studentManagement.Service.SemesterService;
import com.experiment.studentManagement.VO.SemesterVO;
import com.experiment.studentManagement.mapper.SemesterMapper;
import com.experiment.studentManagement.model.Semester;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterMapper semesterMapper;

    @Override
    public void addSemester(SemesterInfoDTO dto) {
        Semester semester = new Semester();
        BeanUtils.copyProperties(dto, semester);
        semesterMapper.insert(semester);
    }

    @Override
    public SemesterVO getById(Integer semesterId) {
        Semester semester = semesterMapper.selectById(semesterId);
        if (semester == null)
            return null;
        return toVO(semester);
    }

    @Override
    public void updateSemester(SemesterInfoDTO dto) {
        Semester semester = new Semester();
        BeanUtils.copyProperties(dto, semester);
        semesterMapper.updateById(semester);
    }

    @Override
    public void deleteById(Integer semesterId) {
        semesterMapper.deleteById(semesterId);
    }

    @Override
    public List<SemesterVO> getAllSemesters() {
        List<Semester> list = semesterMapper.findAll();
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public SemesterVO getCurrentSemester() {
        Semester semester = semesterMapper.findCurrentSemester();
        if (semester == null)
            return null;
        return toVO(semester);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSemesterByYearAndOrder(SemesterDeleteDTO dto) {
        // 1. 根据学年和学期序号查找学期
        Semester semester = semesterMapper.findByAcademicYearAndOrder(
                dto.getAcademicYear(),
                dto.getSemesterOrder());

        if (semester == null) {
            throw new RuntimeException("未找到指定的学期信息");
        }

        Integer semesterId = semester.getSemesterId();

        // 2. 删除该学期的所有成绩记录
        semesterMapper.deleteScoresBySemesterId(semesterId);

        // 3. 删除学期记录
        semesterMapper.deleteById(semesterId);
    }

    @Override
    public List<String> getAllAcademicYears() {
        return semesterMapper.findAllAcademicYears();
    }

    private SemesterVO toVO(Semester semester) {
        SemesterVO vo = new SemesterVO();
        BeanUtils.copyProperties(semester, vo);
        return vo;
    }
}
