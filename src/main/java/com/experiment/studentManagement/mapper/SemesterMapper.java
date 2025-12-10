package com.experiment.studentManagement.mapper;

import com.experiment.studentManagement.model.Semester;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SemesterMapper {
    void insert(Semester semester);

    Semester selectById(@Param("semesterId") Integer semesterId);

    void updateById(Semester semester);

    void deleteById(@Param("semesterId") Integer semesterId);

    List<Semester> findAll();

    Semester findCurrentSemester();

    // 根据学年和学期序号查询学期
    Semester findByAcademicYearAndOrder(@Param("academicYear") String academicYear,
            @Param("semesterOrder") Integer semesterOrder);

    // 查询所有学年
    List<String> findAllAcademicYears();

    // 删除学期相关的所有成绩记录
    void deleteScoresBySemesterId(@Param("semesterId") Integer semesterId);
}
