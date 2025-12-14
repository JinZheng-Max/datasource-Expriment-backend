package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RewardPunishmentInfoDTO {
    private Integer rpId;
    private Integer studentId;
    private String type;
    private String level;
    private String title;
    private String reason;
    private LocalDate awardDate;
    private String issuingUnit;
    private String certificateNo;
    private String certificateUrl;
    private LocalDate cancelDate;
    private String remark;
    private String status;
    private String reviewComment;
}
