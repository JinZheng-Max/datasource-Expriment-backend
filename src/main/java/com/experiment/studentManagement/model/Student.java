package com.experiment.studentManagement.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Student {
    private Integer studentId;
    private String studentNo;
    private String name;
    private String gender;
    private String idCard;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private LocalDate admissionDate;
    private Integer classId;
    private Integer majorId;
    private Integer grade;
    private String photoUrl;
    private String homeAddress;
    private String emergencyContact;
    private String emergencyPhone;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    
    // 用于分页查询时的专业名称和班级名称（非数据库字段）
    private String major;
    private String className;
}





