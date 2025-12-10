package com.experiment.studentManagement.DTO;

import lombok.Data;

/**
 * 实践记录分页查询DTO
 */
@Data
public class PracticePageQueryDTO {
    private Integer page = 1;           // 页码
    private Integer pageSize = 10;      // 每页数量
    private String keyword;             // 关键词（学生姓名/活动名称）
    private String practiceType;        // 活动类型
    private String status;              // 审核状态
}
