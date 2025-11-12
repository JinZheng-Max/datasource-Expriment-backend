package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StatusInfoDTO {
    private Integer statusId;
    private Integer studentId;
    private String status;
    private LocalDate statusDate;
    private Integer currentGrade;
    private String currentMajor;
    private String reason;
    private String remark;
}
