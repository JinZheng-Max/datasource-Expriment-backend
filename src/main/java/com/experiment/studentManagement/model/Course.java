package com.experiment.studentManagement.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Course {
    private Integer courseId;
    private String courseCode;
    private String courseName;
    private String courseType;
    private Double credits;
    private Integer hours;
    private Integer majorId;
    private Integer semesterOrder;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // 扩展字段（非数据库字段）
    private String majorName;
}
