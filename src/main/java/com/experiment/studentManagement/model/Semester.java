package com.experiment.studentManagement.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Semester {
    private Integer semesterId;
    private String semesterCode;
    private String semesterName;
    private String academicYear;
    private Integer semesterOrder;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrent;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
