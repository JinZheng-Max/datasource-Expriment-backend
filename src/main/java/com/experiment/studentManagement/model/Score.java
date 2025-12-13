package com.experiment.studentManagement.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Score {
    private Integer scoreId;
    private Integer studentId;
    private Integer courseId;
    private Integer semesterId;
    private BigDecimal regularScore;
    private BigDecimal midtermScore;
    private BigDecimal finalScore;
    private BigDecimal totalScore;
    private BigDecimal gradePoint;
    private String status; // 正常/缓考/缺考/作弊
    private Boolean isPass;
    private Boolean isRetake;
    private Integer retakeTimes;
    private String teacherName;
    private String remark;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // 扩展字段（非数据库字段）
    private String studentNo;
    private String studentName;
    private String courseName;
    private String courseCode;
    private String semesterName;
    private BigDecimal credits;
}
