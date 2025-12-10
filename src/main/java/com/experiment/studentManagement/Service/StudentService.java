package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.StuInfoDTO;
import com.experiment.studentManagement.DTO.StudentPageQueryDTO;
import com.experiment.studentManagement.VO.StudentVO;
import com.experiment.studentManagement.model.Student;
import com.experiment.studentManagement.result.PageResult;

import java.util.List;

public interface StudentService {
    void addStudent(StuInfoDTO dto);

    PageResult pageQuery(StudentPageQueryDTO dto);

    StudentVO getById(Integer studentId);

    void updateStudent(StuInfoDTO dto);

    void deleteById(Integer studentId);

    List<String> findAllMajor();

    List<String> findAllClass(String major);

    List<Student> getStudentsByGradeAndMajor(Integer grade, String majorName);

}
