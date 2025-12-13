package com.experiment.studentManagement.DTO;

import lombok.Data;

@Data
public class SemesterDeleteDTO {
    private String academicYear; // 学年,如:2024-2025
    private Integer semesterOrder; // 学期序号:1或2
}
