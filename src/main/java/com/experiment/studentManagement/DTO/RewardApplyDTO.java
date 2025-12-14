package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.time.LocalDate;

/**
 * 奖惩申请DTO - 学生提交奖励申请时使用
 */
@Data
public class RewardApplyDTO {
    private String type;              // 类型：奖励/处分（学生只能申请奖励）
    private String level;             // 级别（国家级/省级/校级/院级）
    private String title;             // 奖励名称
    private String reason;            // 原因/事由
    private LocalDate awardDate;      // 获奖日期
    private String issuingUnit;       // 颁发单位
    private String certificateNo;     // 证书编号
    private String certificateUrl;    // 证书路径
    private String applyRemark;       // 申请备注/说明
}
