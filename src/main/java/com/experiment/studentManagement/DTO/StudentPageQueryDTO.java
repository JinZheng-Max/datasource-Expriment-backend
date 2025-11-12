package com.experiment.studentManagement.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    // 查询字段：按姓名模糊查询
    private String name;

    //年级
    private Integer grade;

    //专业
    private String major;

    //性别
    private String gender;
}


