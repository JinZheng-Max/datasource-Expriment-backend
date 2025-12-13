package com.experiment.studentManagement.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StudentPractice {
    private Integer recordId;
    private Integer studentId;
    private Integer practiceId;
    private String role;
    private BigDecimal duration;
    private BigDecimal performanceScore;
    private String evaluation;
    private String certificateUrl;
    private String status; // 待审核/已通过/未通过
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // 扩展字段（非数据库字段）
    private String studentNo;
    private String studentName;
    private String practiceName;
    private String practiceType;
    private String organizer;
}
