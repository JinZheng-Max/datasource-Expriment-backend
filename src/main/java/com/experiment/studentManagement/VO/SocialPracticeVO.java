package com.experiment.studentManagement.VO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SocialPracticeVO {
    private Integer practiceId;
    private String practiceName;
    private String practiceType;
    private String organizer;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
