package com.experiment.studentManagement.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentPractice {
    // student_practice 表字段
    private Integer recordId;           // 记录ID
    private Integer studentId;          // 学生ID
    private Integer practiceId;         // 活动ID
    private String role;                // 担任角色
    private BigDecimal duration;        // 时长（小时）
    private BigDecimal performanceScore; // 表现评分
    private String evaluation;          // 评价
    private String certificateUrl;      // 证明材料路径
    private String status;              // 审核状态
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    
    // social_practice 表字段（用于新增和修改时的活动信息）
    private String practiceName;        // 活动名称
    private String practiceType;        // 活动类型
    private String organizer;           // 组织单位
    private LocalDate startDate;        // 开始日期
    private LocalDate endDate;          // 结束日期
    private String description;         // 活动描述
    
    // 查询时扩展字段（非数据库字段）
    private String studentNo;           // 学号
    private String studentName;         // 学生姓名
}
