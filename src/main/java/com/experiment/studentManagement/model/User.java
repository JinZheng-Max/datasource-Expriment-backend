package com.experiment.studentManagement.model;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String userType;
    private Integer roleId;
    private Integer studentId;
    private String realName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
