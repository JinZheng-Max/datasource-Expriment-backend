package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.StuInfoDTO;
import com.experiment.studentManagement.DTO.StudentPageQueryDTO;
import com.experiment.studentManagement.Service.StudentService;
import com.experiment.studentManagement.VO.StudentVO;
import com.experiment.studentManagement.mapper.ClassMapper;
import com.experiment.studentManagement.mapper.StudentMapper;
import com.experiment.studentManagement.mapper.MajorMapper;
import com.experiment.studentManagement.model.Student;
import com.experiment.studentManagement.result.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private ClassMapper classMapper;



    /**
     * 添加学生
     */
    public void addStudent(StuInfoDTO dto) {
        Student s = new Student();
        BeanUtils.copyProperties(dto, s);
        s.setCreatedTime(LocalDateTime.now());
        s.setUpdatedTime(LocalDateTime.now());
        Integer majorId = majorMapper.findIdByName(dto.getMajor());
        s.setMajorId(majorId);
        Integer classId = classMapper.findClassIdByName(dto.getClassName());
        s.setClassId(classId);
        //todo 添加照片，暂定为 null
        s.setPhotoUrl(null);
        studentMapper.insert(s);
    }

    /**
     * 分页查询
     * 返回 PageResult<Student>，包含指定字段：studentId, studentNo, name, gender, grade, className, phone, admissionDate
     */
    public PageResult pageQuery(StudentPageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        //构造分页查询的条件
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getName());
        params.put("offset", offset);
        params.put("limit", pageSize);

        if (dto.getGrade() != null) {
            params.put("grade", dto.getGrade());
        }

        if (dto.getGender() != null && !dto.getGender().isEmpty()) {
            params.put("gender", dto.getGender());
        }

        if (dto.getMajor() != null && !dto.getMajor().isEmpty()) {
            Integer majorId = majorMapper.findIdByName(dto.getMajor());
            if (majorId == null) {
                return new PageResult(0, Collections.emptyList());
            }
            params.put("majorId", majorId);
        }

        long total = studentMapper.countByCondition(params);
        List<Student> list = studentMapper.pageByCondition(params);

        // 直接返回 Student 列表
        return new PageResult(total, list);
    }

    /**
     * 根据ID查询
     */
    public StudentVO getById(Integer studentId) {
        Student s = studentMapper.selectById(studentId);
        if (s == null) return null;
        //根据班级名称查询班级名称
        String className = classMapper.findClassNameById(s.getClassId());
        s.setClassName(className);
        //根据专业名称查询专业名称
        String major = majorMapper.findMajorNameById(s.getMajorId());
        s.setMajor(major);

        return toVO(s);
    }

    /**
     * 修改学生信息
     */
    public void updateStudent(StuInfoDTO dto) {
        Student s = new Student();
        BeanUtils.copyProperties(dto, s);
        
        // 如果传入了专业名称，转换为专业ID
        if (dto.getMajor() != null && !dto.getMajor().isEmpty()) {
            Integer majorId = majorMapper.findIdByName(dto.getMajor());
            s.setMajorId(majorId);
        }
        
        // 如果传入了班级名称，转换为班级ID
        if (dto.getClassName() != null && !dto.getClassName().isEmpty()) {
            Integer classId = classMapper.findClassIdByName(dto.getClassName());
            s.setClassId(classId);
        }
        
        studentMapper.updateById(s);
    }

    /**
     * 删除学生
     */
    public void deleteById(Integer studentId) {
        studentMapper.deleteById(studentId);
    }

    /**
     * 查询所有专业名称
     */
    public List<String> findAllMajor() {
        return majorMapper.findAll();
    }

    /**
     * 查询对应专业班级名称
     */
    public List<String> findAllClass(String major) {
        //根据专业名称查询专业id
        Integer majorId = majorMapper.findIdByName(major);
        //根据专业id查询班级名称
        return classMapper.findAllClassName(majorId);
    }

    private StudentVO toVO(Student s) {
        StudentVO vo = new StudentVO();
        BeanUtils.copyProperties(s, vo);
        return vo;
    }
}


