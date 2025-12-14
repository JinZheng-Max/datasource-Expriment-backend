package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.StuInfoDTO;
import com.experiment.studentManagement.DTO.StudentPageQueryDTO;
import com.experiment.studentManagement.Service.StudentService;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
     * 增加学生
     * 
     * @param StuInfoDTO
     */
    @PostMapping("/add")
    public Result addStudent(@RequestBody StuInfoDTO dto) {
        log.info("增加学生：{}", dto);
        studentService.addStudent(dto);
        return Result.success();
    }

    // 根据ID查询
    @GetMapping("/{studentId}")
    public Result getById(@PathVariable Integer studentId) {
        return Result.success(studentService.getById(studentId));
    }

    // 修改学生信息
    @PutMapping("/update")
    public Result update(@RequestBody StuInfoDTO dto) {
        log.info("修改学生信息： dto={}", dto);
        studentService.updateStudent(dto);
        return Result.success();
    }

    // 删除学生
    @DeleteMapping("/delete/{studentId}")
    public Result delete(@PathVariable Integer studentId) {
        log.info("删除学生：{}", studentId);
        studentService.deleteById(studentId);
        return Result.success();
    }

    /*
     * 分页查询学生信息
     * 
     * @param StudentPageQueryDTO
     */
    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody StudentPageQueryDTO dto) {
        log.info("分页查询：{}", dto);
        PageResult pageResult = studentService.pageQuery(dto);
        return Result.success(pageResult);
    }

    /**
     * 查询所有专业
     */
    @GetMapping("/major")
    public Result<List<String>> findAllMajor() {
        log.info("查询所有专业");
        List<String> list = studentService.findAllMajor();
        return Result.success(list);
    }

    /**
     * 查询对应专业的所有班级
     * 
     * @param major
     */
    @GetMapping("/class")
    public Result<List<String>> findAllClass(String major) {
        log.info("查询对应专业的所有班级：{}", major);
        List<String> list = studentService.findAllClass(major);
        return Result.success(list);
    }

    /**
     * 查询所有学生
     */
    @GetMapping("/list")
    public Result getAllStudents() {
        log.info("查询所有学生");
        return Result.success(studentService.getAllStudents());
    }
}
