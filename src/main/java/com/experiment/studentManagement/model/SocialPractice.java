package com.experiment.studentManagement.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SocialPractice {
    private Integer practiceId;
    private String practiceName;
    private String practiceType;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
