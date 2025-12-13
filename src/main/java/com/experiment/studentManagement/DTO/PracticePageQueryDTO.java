package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class PracticePageQueryDTO implements Serializable {
    private int page;
    private int pageSize;
    private String name; // 活动名称或学生姓名
    private String practiceType; // 活动类型
    private String status; // 审核状态
}
