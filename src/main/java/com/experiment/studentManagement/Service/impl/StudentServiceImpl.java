package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.StuInfoDTO;
import com.experiment.studentManagement.DTO.StudentPageQueryDTO;
import com.experiment.studentManagement.Service.StudentService;
import com.experiment.studentManagement.VO.StudentVO;
import com.experiment.studentManagement.mapper.ClassMapper;
import com.experiment.studentManagement.mapper.StudentMapper;
import com.experiment.studentManagement.mapper.MajorMapper;
import com.experiment.studentManagement.mapper.UserMapper;
import com.experiment.studentManagement.model.Student;
import com.experiment.studentManagement.model.User;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加学生，同时自动创建学生账号（账号为学号，密码为123456）
     */
    @Transactional
    public void addStudent(StuInfoDTO dto) {
        Student s = new Student();
        BeanUtils.copyProperties(dto, s);
        s.setCreatedTime(LocalDateTime.now());
        s.setUpdatedTime(LocalDateTime.now());
        Integer majorId = majorMapper.findIdByName(dto.getMajor());
        s.setMajorId(majorId);
        Integer classId = classMapper.findClassIdByName(dto.getClassName());
        s.setClassId(classId);
        // todo 添加照片，暂定为 null
        s.setPhotoUrl(null);
        studentMapper.insert(s);

        // 自动创建学生账号
        User user = new User();
        user.setUsername(dto.getStudentNo());
        user.setPassword(MD5Util.encrypt("123456"));
        user.setUserType("student");
        user.setRoleId(2); // 学生角色ID
        user.setStudentId(s.getStudentId());
        user.setRealName(dto.getName());
        userMapper.insert(user);
    }

    /**
     * 分页查询
     * 返回 PageResult<Student>，包含指定字段：studentId, studentNo, name, gender, grade,
     * className, phone, admissionDate
     */
    public PageResult pageQuery(StudentPageQueryDTO dto) {
        int page = Math.max(dto.getPage(), 1);
        int pageSize = Math.max(dto.getPageSize(), 10);
        int offset = (page - 1) * pageSize;

        // 构造分页查询的条件
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

        if (s == null)
            return null;
        // 根据班级id查询班级名称
        String className = classMapper.findClassNameById(s.getClassId());
        s.setClassName(className);
        // 根据专业名称查询专业名称
        String major = majorMapper.findMajorNameById(s.getMajorId());
        s.setMajor(major);

        return toVO(s);
    }

    /**
     * 修改学生信息
     */
    public void updateStudent(StuInfoDTO dto) {
        // 【关键】验证 studentId 是否存在
        if (dto.getStudentId() == null) {
            throw new IllegalArgumentException("学生ID不能为空");
        }

        // 验证学生是否存在
        Student existStudent = studentMapper.selectById(dto.getStudentId());
        if (existStudent == null) {
            throw new IllegalArgumentException("学生不存在");
        }

        Student s = new Student();
        BeanUtils.copyProperties(dto, s);

        // 如果传入了专业名称，转换为专业ID
        if (dto.getMajor() != null && !dto.getMajor().isEmpty()) {
            Integer majorId = majorMapper.findIdByName(dto.getMajor());
            if (majorId == null) {
                throw new IllegalArgumentException("专业不存在");
            }
            s.setMajorId(majorId);
        }

        // 如果传入了班级名称，转换为班级ID
        if (dto.getClassName() != null && !dto.getClassName().isEmpty()) {
            Integer classId = classMapper.findClassIdByName(dto.getClassName());
            if (classId == null) {
                throw new IllegalArgumentException("班级不存在");
            }
            s.setClassId(classId);
        }

        // 【关键】确保 studentId 被设置
        s.setStudentId(dto.getStudentId());

        // 执行更新
        int updateCount = studentMapper.updateById(s);
        if (updateCount == 0) {
            throw new RuntimeException("更新失败");
        }
    }

    /**
     * 删除学生，同时删除对应的用户账号
     */
    @Transactional
    public void deleteById(Integer studentId) {
        userMapper.deleteByStudentId(studentId);
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
        // 根据专业名称查询专业id
        Integer majorId = majorMapper.findIdByName(major);
        // 根据专业id查询班级名称
        return classMapper.findAllClassName(majorId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    private StudentVO toVO(Student s) {
        StudentVO vo = new StudentVO();
        BeanUtils.copyProperties(s, vo);
        return vo;
    }
}
