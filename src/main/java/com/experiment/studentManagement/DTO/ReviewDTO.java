package com.experiment.studentManagement.DTO;

import lombok.Data;

/**
 * 审核DTO - 管理员审核时使用
 */
@Data
public class ReviewDTO {
    private Integer id;               // 记录ID（实践记录ID或奖惩ID）
    private String status;            // 审核状态：已通过/未通过
    private String reviewComment;     // 审核意见
    private java.math.BigDecimal performanceScore;  // 表现评分（仅社会实践）
    private String evaluation;        // 评价（仅社会实践）
}
