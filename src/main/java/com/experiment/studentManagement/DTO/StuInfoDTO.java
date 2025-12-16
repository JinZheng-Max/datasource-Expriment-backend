package com.experiment.studentManagement.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StuInfoDTO {
    private Integer studentId; // 【关键】编辑时需要此字段
    private String studentNo;
    private String name;
    private String gender;
    private String idCard;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private LocalDate admissionDate;
    private Integer grade;
    private String major;
    private String className;
    private String homeAddress;
    private String emergencyContact;
    private String emergencyPhone;
}

/**
 * model
 * public class Student {
 * private Integer studentId;
 * private String studentNo;
 * private String name;
 * private String gender;
 * private String idCard;
 * private LocalDate birthDate;
 * private String phone;
 * private String email;
 * private LocalDateTime admissionDate;
 * private Integer classId;
 * private Integer majorId;
 * private Integer grade;
 * private String photoUrl;
 * private String address;
 * private String emergencyContact;
 * private String emergencyPhone;
 * private LocalDateTime createdTime;
 * private LocalDateTime updatedTime;
 * }
 * 
 */