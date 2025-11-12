package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.StudentStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentStatusMapper {
    void insert(StudentStatus status);

    StudentStatus selectById(@Param("statusId") Integer statusId);

    void updateById(StudentStatus status);

    void deleteById(@Param("statusId") Integer statusId);

    long countByCondition(Map<String, Object> params);

    List<StudentStatus> pageByCondition(Map<String, Object> params);

    /**
     * 查询没有学籍记录的学生ID列表
     */
    List<Integer> findStudentsWithoutStatus();

    /**
     * 批量插入默认学籍信息
     */
    void batchInsertDefaultStatus(@Param("list") List<StudentStatus> list);

    /**
     * 根据学生ID删除学籍记录
     */
    void deleteByStudentId(@Param("studentId") Integer studentId);
}
