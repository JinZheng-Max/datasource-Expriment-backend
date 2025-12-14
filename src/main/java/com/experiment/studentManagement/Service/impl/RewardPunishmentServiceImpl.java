package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.RewardApplyDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentInfoDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentPageQueryDTO;
import com.experiment.studentManagement.DTO.ReviewDTO;
import com.experiment.studentManagement.Service.RewardPunishmentService;
import com.experiment.studentManagement.VO.RewardPunishmentVO;
import com.experiment.studentManagement.mapper.RewardPunishmentMapper;
import com.experiment.studentManagement.model.RewardPunishment;
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
public class RewardPunishmentServiceImpl implements RewardPunishmentService {

    @Autowired
    private RewardPunishmentMapper rpMapper;

    @Override
    public void add(RewardPunishmentInfoDTO dto) {
        RewardPunishment rp = new RewardPunishment();
        BeanUtils.copyProperties(dto, rp);
        // 处分不参与审核：录入即生效
        if ("处分".equals(rp.getType())) {
            rp.setStatus("已通过");
            rp.setApplyRemark(null);
            rp.setReviewerId(null);
            rp.setReviewTime(null);
            rp.setReviewComment(null);
        }
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
        // 处分不参与审核：强制已通过，并清空审核字段
        if ("处分".equals(rp.getType())) {
            rp.setStatus("已通过");
            rp.setApplyRemark(null);
            rp.setReviewerId(null);
            rp.setReviewTime(null);
            rp.setReviewComment(null);
        }
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

    @Override
    public List<RewardPunishmentVO> getByStudentId(Integer studentId) {
        List<RewardPunishment> list = rpMapper.findByStudentId(studentId);
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    private RewardPunishmentVO toVO(RewardPunishment rp) {
        RewardPunishmentVO vo = new RewardPunishmentVO();
        BeanUtils.copyProperties(rp, vo);
        return vo;
    }

    @Override
    public void apply(Integer studentId, RewardApplyDTO dto) {
        RewardPunishment rp = new RewardPunishment();
        rp.setStudentId(studentId);
        rp.setType("奖励"); // 学生只能申请奖励
        rp.setLevel(dto.getLevel());
        rp.setTitle(dto.getTitle());
        rp.setReason(dto.getReason());
        rp.setAwardDate(dto.getAwardDate());
        rp.setIssuingUnit(dto.getIssuingUnit());
        rp.setCertificateNo(dto.getCertificateNo());
        rp.setCertificateUrl(dto.getCertificateUrl());
        rp.setApplyRemark(dto.getApplyRemark());
        rp.setApplyTime(LocalDateTime.now());
        rp.setStatus("待审核");
        rpMapper.insert(rp);
    }

    @Override
    public void review(Integer reviewerId, ReviewDTO dto) {
        RewardPunishment rp = rpMapper.selectById(dto.getId());
        if (rp == null) {
            throw new RuntimeException("记录不存在");
        }
        if ("处分".equals(rp.getType())) {
            throw new RuntimeException("处分无需审核");
        }
        if (!"待审核".equals(rp.getStatus())) {
            throw new RuntimeException("该记录已审核");
        }
        rp.setStatus(dto.getStatus());
        rp.setReviewerId(reviewerId);
        rp.setReviewTime(LocalDateTime.now());
        rp.setReviewComment(dto.getReviewComment());
        rpMapper.updateById(rp);
    }

    @Override
    public PageResult getPendingList(RewardPunishmentPageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("type", dto.getType());
        params.put("level", dto.getLevel());
        params.put("status", "待审核");
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = rpMapper.countByCondition(params);
        List<RewardPunishment> list = rpMapper.pageByCondition(params);

        List<RewardPunishmentVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }
}
