package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.StatusInfoDTO;
import com.experiment.studentManagement.DTO.StatusPageQueryDTO;
import com.experiment.studentManagement.Service.StudentStatusService;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
@Slf4j
public class StudentStatusController {

    private final StudentStatusService statusService;

    public StudentStatusController(StudentStatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{statusId:\\d+}")
    public Result getById(@PathVariable Integer statusId) {
        return Result.success(statusService.getById(statusId));
    }

    /**
     * 根据学生ID查询学籍
     */
    @GetMapping("/student/{studentId}")
    public Result getByStudentId(@PathVariable Integer studentId) {
        log.info("根据学生ID查询学籍：{}", studentId);
        return Result.success(statusService.getByStudentId(studentId));
    }

    @PutMapping("/update")
    public Result update(@RequestBody StatusInfoDTO dto) {
        log.info("更新学籍状态：{}", dto);
        statusService.updateStatus(dto);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody StatusInfoDTO dto) {
        log.info("添加学籍状态：{}", dto);
        statusService.addStatus(dto);
        return Result.success();
    }

    @DeleteMapping("/delete/{statusId}")
    public Result delete(@PathVariable Integer statusId) {
        log.info("删除学籍状态：{}", statusId);
        statusService.deleteById(statusId);
        return Result.success();
    }

    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody StatusPageQueryDTO dto) {
        log.info("分页查询学籍状态：{}", dto);
        PageResult pageResult = statusService.pageQuery(dto);
        return Result.success(pageResult);
    }

    /**
     * 初始化学籍信息 - 为没有学籍记录的学生创建默认记录
     */
    @PostMapping("/initialize")
    public Result initialize() {
        log.info("初始化学籍信息");
        statusService.initializeStatus();
        return Result.success();
    }
}
