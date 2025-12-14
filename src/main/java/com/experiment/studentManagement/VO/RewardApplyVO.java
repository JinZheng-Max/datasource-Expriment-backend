package com.experiment.studentManagement.VO;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 奖惩申请VO - 包含审核信息
 */
@Data
public class RewardApplyVO {
    private Integer rpId;
    private Integer studentId;
    private String studentNo;
    private String studentName;
    
    // 奖惩信息
    private String type;
    private String level;
    private String title;
    private String reason;
    private LocalDate awardDate;
    private String issuingUnit;
    private String certificateNo;
    private String certificateUrl;
    private String remark;
    
    // 申请信息
    private String applyRemark;
    private LocalDateTime applyTime;
    
    // 审核信息
    private String status;
    private Integer reviewerId;
    private String reviewerName;
    private LocalDateTime reviewTime;
    private String reviewComment;
}
