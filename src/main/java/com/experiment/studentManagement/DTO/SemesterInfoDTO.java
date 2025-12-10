package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SemesterInfoDTO {
    private Integer semesterId;
    private String semesterCode;
    private String semesterName;
    private String academicYear;
    private Integer semesterOrder;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrent;
}
