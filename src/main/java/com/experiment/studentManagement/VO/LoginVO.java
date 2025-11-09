package com.experiment.studentManagement.VO;

import lombok.Data;

@Data
public class LoginVO {
    private Integer userId;
    private String username;
    private String realName;
    private String userType;
    private String token;
}


