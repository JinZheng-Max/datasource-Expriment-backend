package com.experiment.studentManagement.VO;

import lombok.Data;

@Data
public class CourseVO {
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private String courseType;
    private Double credits;
    private Integer hours;
    private String majorName;
    private Integer semesterOrder;
    private String description;
}
