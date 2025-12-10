package com.experiment.studentManagement.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 实践记录视图对象（用于返回给前端）
 */
@Data
public class PracticeVO {
    private Integer recordId;           // 记录ID
    private Integer studentId;          // 学生ID
    private String studentNo;           // 学号
    private String studentName;         // 学生姓名
    private Integer practiceId;         // 活动ID
    private String practiceName;        // 活动名称
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
