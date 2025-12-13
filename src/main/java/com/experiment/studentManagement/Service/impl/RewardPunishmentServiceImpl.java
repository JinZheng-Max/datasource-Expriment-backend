package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.RewardPunishmentInfoDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentPageQueryDTO;
import com.experiment.studentManagement.Service.RewardPunishmentService;
import com.experiment.studentManagement.VO.RewardPunishmentVO;
import com.experiment.studentManagement.mapper.RewardPunishmentMapper;
import com.experiment.studentManagement.model.RewardPunishment;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardPunishmentServiceImpl implements RewardPunishmentService {

    @Autowired
    private RewardPunishmentMapper rpMapper;

    @Override
    public void add(RewardPunishmentInfoDTO dto) {
        RewardPunishment rp = new RewardPunishment();
        BeanUtils.copyProperties(dto, rp);
        rpMapper.insert(rp);
    }

    @Override
    public PageResult pageQuery(RewardPunishmentPageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("type", dto.getType());
        params.put("level", dto.getLevel());
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = rpMapper.countByCondition(params);
        List<RewardPunishment> list = rpMapper.pageByCondition(params);

        List<RewardPunishmentVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }

    @Override
    public RewardPunishmentVO getById(Integer rpId) {
        RewardPunishment rp = rpMapper.selectById(rpId);
        if (rp == null)
            return null;
        return toVO(rp);
    }

    @Override
    public void update(RewardPunishmentInfoDTO dto) {
        RewardPunishment rp = new RewardPunishment();
        BeanUtils.copyProperties(dto, rp);
        rpMapper.updateById(rp);
    }

    @Override
    public void deleteById(Integer rpId) {
        rpMapper.deleteById(rpId);
    }

    @Override
    public List<RewardPunishmentVO> getAll() {
        List<RewardPunishment> list = rpMapper.findAll();
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    private RewardPunishmentVO toVO(RewardPunishment rp) {
        RewardPunishmentVO vo = new RewardPunishmentVO();
        BeanUtils.copyProperties(rp, vo);
        return vo;
    }
}
