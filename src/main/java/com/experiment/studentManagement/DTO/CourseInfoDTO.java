package com.experiment.studentManagement.DTO;

import lombok.Data;

@Data
public class CourseInfoDTO {
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private String courseType;
    private Double credits;
    private Integer hours;
    private Integer majorId;
    private Integer semesterOrder;
    private String description;
}
