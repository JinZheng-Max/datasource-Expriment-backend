package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.StudentPractice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PracticeMapper {
    /**
     * 插入活动信息，返回生成的practiceId
     */
    void insertPractice(StudentPractice practice);
    
    /**
     * 插入学生参与记录
     */
    void insertStudentPractice(StudentPractice practice);
    
    /**
     * 根据recordId查询详情
     */
    StudentPractice selectByRecordId(@Param("recordId") Integer recordId);
    
    /**
     * 更新活动信息
     */
    void updatePractice(StudentPractice practice);
    
    /**
     * 更新学生参与记录
     */
    void updateStudentPractice(StudentPractice practice);
    
    /**
     * 根据recordId删除学生参与记录
     */
    void deleteByRecordId(@Param("recordId") Integer recordId);
    
    /**
     * 根据practiceId删除活动信息
     */
    void deleteByPracticeId(@Param("practiceId") Integer practiceId);
    
    /**
     * 分页查询总数
     */
    long countByCondition(Map<String, Object> params);
    
    /**
     * 分页查询记录列表
     */
    List<StudentPractice> pageByCondition(Map<String, Object> params);
}
