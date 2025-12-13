package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SocialPracticeInfoDTO {
    private Integer practiceId;
    private String practiceName;
    private String practiceType;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
