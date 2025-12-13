package com.experiment.studentManagement.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentStatus {
    private Integer statusId;
    private Integer studentId;
    private String status;
    private LocalDate statusDate;
    private String reason;
    private String remark;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // 用于查询时的扩展字段（非数据库字段）
    private String studentNo;
    private String name;
    private Integer currentGrade; // 从student表的grade字段获取
    private String majorName;
    private String className;
}
