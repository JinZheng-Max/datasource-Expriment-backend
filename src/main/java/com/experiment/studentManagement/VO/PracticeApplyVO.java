package com.experiment.studentManagement.VO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 社会实践申请VO - 包含审核信息
 */
@Data
public class PracticeApplyVO {
    private Integer recordId;
    private Integer studentId;
    private String studentNo;
    private String studentName;
    
    // 活动信息
    private Integer practiceId;
    private String practiceName;
    private String practiceType;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    
    // 申请信息
    private String role;
    private BigDecimal duration;
    private String certificateUrl;
    private String applyRemark;
    private LocalDateTime applyTime;
    
    // 审核信息
    private String status;
    private BigDecimal performanceScore;
    private String evaluation;
    private Integer reviewerId;
    private String reviewerName;
    private LocalDateTime reviewTime;
    private String reviewComment;
}
