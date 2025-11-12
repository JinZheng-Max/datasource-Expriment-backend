package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class StatusPageQueryDTO implements Serializable {
    private int page;
    private int pageSize;
    private String name;
    private String status;
    private Integer grade;
}
