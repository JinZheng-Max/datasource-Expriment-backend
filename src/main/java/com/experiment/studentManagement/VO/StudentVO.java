package com.experiment.studentManagement.VO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentVO {
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


