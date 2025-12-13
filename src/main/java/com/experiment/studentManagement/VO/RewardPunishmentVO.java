package com.experiment.studentManagement.VO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RewardPunishmentVO {
    private Integer rpId;
    private Integer studentId;
    private String studentNo;
    private String studentName;
    private String majorName;
    private String className;
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
}
