package com.experiment.studentManagement.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 实践记录信息DTO（用于新增和修改）
 */
@Data
public class PracticeInfoDTO {
    private Integer recordId;           // 记录ID（修改时必填）
    private Integer studentId;          // 学生ID（必填）
    private String practiceName;        // 活动名称（必填）
    private String practiceType;        // 活动类型
    private String organizer;           // 组织单位
    private String role;                // 担任角色
    private LocalDate startDate;        // 开始日期
    private LocalDate endDate;          // 结束日期
    private BigDecimal duration;        // 时长（小时）
    private BigDecimal performanceScore; // 表现评分
    private String description;         // 活动描述
    private String evaluation;          // 评价
    private String status;              // 审核状态
}
