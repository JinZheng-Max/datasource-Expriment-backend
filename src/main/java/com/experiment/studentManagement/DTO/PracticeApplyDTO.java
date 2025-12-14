package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 社会实践申请DTO - 学生提交申请时使用
 */
@Data
public class PracticeApplyDTO {
    private Integer practiceId;       // 活动ID
    private String role;              // 担任角色
    private BigDecimal duration;      // 时长（小时）
    private String applyRemark;       // 申请备注/说明
    private String certificateUrl;    // 证明材料路径
}
