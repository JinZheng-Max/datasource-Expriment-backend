package com.experiment.studentManagement.VO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StatusVO {
    private Integer statusId;
    private Integer studentId;
    private String studentNo;
    private String name;
    private String status;
    private Integer currentGrade; // 从student表获取
    private String majorName; // 从student表获取
    private String className; // 从student表获取
    private LocalDate statusDate;
    private String reason;
    private String remark;
}
