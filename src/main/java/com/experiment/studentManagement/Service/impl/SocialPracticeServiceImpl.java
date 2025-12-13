package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.SocialPracticeInfoDTO;
import com.experiment.studentManagement.Service.SocialPracticeService;
import com.experiment.studentManagement.VO.SocialPracticeVO;
import com.experiment.studentManagement.mapper.SocialPracticeMapper;
import com.experiment.studentManagement.model.SocialPractice;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SocialPracticeServiceImpl implements SocialPracticeService {

    @Autowired
    private SocialPracticeMapper practiceMapper;

    @Override
    public void addPractice(SocialPracticeInfoDTO dto) {
        SocialPractice practice = new SocialPractice();
        BeanUtils.copyProperties(dto, practice);
        practiceMapper.insert(practice);
    }

    @Override
    public PageResult pageQuery(PracticePageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("practiceType", dto.getPracticeType());
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = practiceMapper.countByCondition(params);
        List<SocialPractice> list = practiceMapper.pageByCondition(params);

        List<SocialPracticeVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }

    @Override
    public SocialPracticeVO getById(Integer practiceId) {
        SocialPractice practice = practiceMapper.selectById(practiceId);
        if (practice == null)
            return null;
        return toVO(practice);
    }

    @Override
    public void updatePractice(SocialPracticeInfoDTO dto) {
        SocialPractice practice = new SocialPractice();
        BeanUtils.copyProperties(dto, practice);
        practiceMapper.updateById(practice);
    }

    @Override
    public void deleteById(Integer practiceId) {
        practiceMapper.deleteById(practiceId);
    }

    @Override
    public List<SocialPracticeVO> getAllPractices() {
        List<SocialPractice> list = practiceMapper.findAll();
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    private SocialPracticeVO toVO(SocialPractice practice) {
        SocialPracticeVO vo = new SocialPracticeVO();
        BeanUtils.copyProperties(practice, vo);
        return vo;
    }
}
