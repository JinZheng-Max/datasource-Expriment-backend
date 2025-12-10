package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ScoreInfoDTO {
    private Integer scoreId;
    private Integer studentId;
    private Integer courseId;
    private Integer semesterId;
    private BigDecimal regularScore;
    private BigDecimal midtermScore;
    private BigDecimal finalScore;
    private BigDecimal totalScore;
    private BigDecimal gradePoint;
    private String status;
    private Boolean isPass;
    private Boolean isRetake;
    private Integer retakeTimes;
    private String teacherName;
    private String remark;
}
