package com.experiment.studentManagement.DTO;

import lombok.Data;

@Data
public class ScoreEntryDTO {
    //学号
    private String studentNo;
    //姓名
    private String name;
    //课程名称
    private String courseName;
    //学期
    private String semester;
    //平时成绩
    private Double regularScore;
    //期中考试成绩
    private Double midtermScore;
    //期末考试成绩
    private Double finalScore;
    //总成绩
    private Double totalScore;
    //绩点
    private Double gradePoint;
    //考试状态
    private String status;
    //授课教师
    private String teacherName;
    //是否重修
    private Boolean isRetake;
    //备注
    private String remark;
}
