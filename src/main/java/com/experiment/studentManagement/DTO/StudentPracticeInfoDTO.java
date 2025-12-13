package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StudentPracticeInfoDTO {
    private Integer recordId;
    private Integer studentId;
    private Integer practiceId;
    private String role;
    private BigDecimal duration;
    private BigDecimal performanceScore;
    private String evaluation;
    private String certificateUrl;
    private String status;
}
