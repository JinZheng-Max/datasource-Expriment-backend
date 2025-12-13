package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class CoursePageQueryDTO implements Serializable {
    private int page;
    private int pageSize;
    private String name;
    private String courseType;
    private Integer majorId;
}
