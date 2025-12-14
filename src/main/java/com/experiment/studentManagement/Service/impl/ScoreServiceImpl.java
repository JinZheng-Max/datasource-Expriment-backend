package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.ScoreInfoDTO;
import com.experiment.studentManagement.DTO.ScorePageQueryDTO;
import com.experiment.studentManagement.Service.ScoreService;
import com.experiment.studentManagement.VO.ScoreVO;
import com.experiment.studentManagement.mapper.ScoreMapper;
import com.experiment.studentManagement.model.Score;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public void addScore(ScoreInfoDTO dto) {
        Score score = new Score();
        BeanUtils.copyProperties(dto, score);
        scoreMapper.insert(score);
    }

    @Override
    public PageResult pageQuery(ScorePageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("semesterId", dto.getSemesterId());
        params.put("courseId", dto.getCourseId());
        params.put("status", dto.getStatus());
        params.put("isPass", dto.getIsPass());
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = scoreMapper.countByCondition(params);
        List<Score> list = scoreMapper.pageByCondition(params);

        List<ScoreVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }

    @Override
    public ScoreVO getById(Integer scoreId) {
        Score score = scoreMapper.selectById(scoreId);
        if (score == null)
            return null;
        return toVO(score);
    }

    @Override
    public void updateScore(ScoreInfoDTO dto) {
        Score score = new Score();
        BeanUtils.copyProperties(dto, score);
        scoreMapper.updateById(score);
    }

    @Override
    public void deleteById(Integer scoreId) {
        scoreMapper.deleteById(scoreId);
    }

    @Override
    public List<ScoreVO> getAllScores() {
        List<Score> list = scoreMapper.findAll();
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public List<ScoreVO> getByStudentId(Integer studentId) {
        List<Score> list = scoreMapper.findByStudentId(studentId);
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    private ScoreVO toVO(Score score) {
        ScoreVO vo = new ScoreVO();
        BeanUtils.copyProperties(score, vo);
        return vo;
    }
}