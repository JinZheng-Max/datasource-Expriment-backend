package com.experiment.studentManagement.VO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StudentPracticeVO {
    private Integer recordId;
    private Integer studentId;
    private String studentNo;
    private String studentName;
    private Integer practiceId;
    private String practiceName;
    private String practiceType;
    private String organizer;
    private String role;
    private BigDecimal duration;
    private BigDecimal performanceScore;
    private String evaluation;
    private String certificateUrl;
    private String status;
}
