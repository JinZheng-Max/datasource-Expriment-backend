package com.experiment.studentManagement.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RewardPunishment {
    private Integer rpId;
    private Integer studentId;
    private String type; // 奖励/处分
    private String level;
    private String title;
    private String reason;
    private LocalDate awardDate;
    private String issuingUnit;
    private String certificateNo;
    private String certificateUrl;
    private LocalDate cancelDate;
    private String remark;
    private String status; // 待审核/已通过/未通过
    private LocalDateTime applyTime;
    private String applyRemark;
    private Integer reviewerId;
    private LocalDateTime reviewTime;
    private String reviewComment;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // 扩展字段（非数据库字段）
    private String studentNo;
    private String studentName;
    private String majorName;
    private String className;
    private String reviewerName;
}
