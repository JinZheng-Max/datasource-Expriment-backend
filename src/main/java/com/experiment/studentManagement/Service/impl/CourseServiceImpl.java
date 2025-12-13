package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.CourseInfoDTO;
import com.experiment.studentManagement.DTO.CoursePageQueryDTO;
import com.experiment.studentManagement.Service.CourseService;
import com.experiment.studentManagement.VO.CourseVO;
import com.experiment.studentManagement.mapper.CourseMapper;
import com.experiment.studentManagement.model.Course;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void addCourse(CourseInfoDTO dto) {
        Course course = new Course();
        BeanUtils.copyProperties(dto, course);
        courseMapper.insert(course);
    }

    @Override
    public PageResult pageQuery(CoursePageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("courseType", dto.getCourseType());
        params.put("majorId", dto.getMajorId());
        params.put("offset", offset);
        params.put("limit", pageSize);

        long total = courseMapper.countByCondition(params);
        List<Course> list = courseMapper.pageByCondition(params);

        List<CourseVO> voList = list.stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult(total, voList);
    }

    @Override
    public CourseVO getById(Integer courseId) {
        Course course = courseMapper.selectById(courseId);
        if (course == null)
            return null;
        return toVO(course);
    }

    @Override
    public void updateCourse(CourseInfoDTO dto) {
        Course course = new Course();
        BeanUtils.copyProperties(dto, course);
        courseMapper.updateById(course);
    }

    @Override
    public void deleteById(Integer courseId) {
        courseMapper.deleteById(courseId);
    }

    @Override
    public List<CourseVO> getAllCourses() {
        List<Course> list = courseMapper.findAll();
        return list.stream().map(this::toVO).collect(Collectors.toList());
    }

    private CourseVO toVO(Course course) {
        CourseVO vo = new CourseVO();
        BeanUtils.copyProperties(course, vo);
        return vo;
    }
}
