package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class ScorePageQueryDTO implements Serializable {
    private int page;
    private int pageSize;
    private String name; // 学生姓名或学号
    private Integer semesterId; // 学期ID
    private Integer courseId; // 课程ID
    private String status; // 考试状态
    private Boolean isPass; // 是否通过
}
