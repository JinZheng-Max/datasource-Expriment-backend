package com.experiment.studentManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.experiment.studentManagement.model.Student;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    void insert(Student student);

    Student selectById(@Param("studentId") Integer studentId);

    void updateById(Student student);

    void deleteById(@Param("studentId") Integer studentId);

    long countByCondition(Map<String, Object> params);

    List<Student> pageByCondition(Map<String, Object> params);

    @Select("select s.student_id, s.student_no, s.name, s.grade, m.major_name as major " +
            "from student s " +
            "left join major m on s.major_id = m.major_id " +
            "where s.grade = #{grade} and m.major_name = #{majorName} " +
            "order by s.student_no")
    List<Student> findByGradeAndMajor(@Param("grade") Integer grade, @Param("majorName") String majorName);
}
