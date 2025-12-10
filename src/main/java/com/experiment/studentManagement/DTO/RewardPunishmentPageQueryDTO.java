package com.experiment.studentManagement.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class RewardPunishmentPageQueryDTO implements Serializable {
    private int page;
    private int pageSize;
    private String name; // 学生姓名或学号
    private String type; // 奖励/处分
    private String level; // 级别
}
