package com.experiment.studentManagement.VO;

import lombok.Data;

@Data
public class ProfileVO {
    private Integer userId;
    private String username;
    private String realName;
    private String userType;
    private StudentVO studentInfo;
}
