package com.experiment.studentManagement.Service;

import com.experiment.studentManagement.DTO.CourseInfoDTO;
import com.experiment.studentManagement.DTO.CoursePageQueryDTO;
import com.experiment.studentManagement.VO.CourseVO;
import com.experiment.studentManagement.result.PageResult;

import java.util.List;
import java.util.Map;

public interface CourseService {
    void addCourse(CourseInfoDTO dto);

    PageResult pageQuery(CoursePageQueryDTO dto);

    CourseVO getById(Integer courseId);

    void updateCourse(CourseInfoDTO dto);

    void deleteById(Integer courseId);

    List<CourseVO> getAllCourses();

    /**
     * 获取所有专业列表(包含ID和名称)
     */
    List<Map<String, Object>> getAllMajorsWithId();
}
