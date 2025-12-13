package com.experiment.studentManagement.VO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ScoreVO {
    private Integer scoreId;
    private Integer studentId;
    private String studentNo;
    private String studentName;
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private BigDecimal credits;
    private Integer semesterId;
    private String semesterName;
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
