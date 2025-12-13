package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.SocialPracticeInfoDTO;
import com.experiment.studentManagement.DTO.StudentPracticeInfoDTO;
import com.experiment.studentManagement.Service.SocialPracticeService;
import com.experiment.studentManagement.Service.StudentPracticeService;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/practice")
@Slf4j
public class SocialPracticeController {

    private final SocialPracticeService practiceService;
    private final StudentPracticeService studentPracticeService;

    public SocialPracticeController(SocialPracticeService practiceService,
            StudentPracticeService studentPracticeService) {
        this.practiceService = practiceService;
        this.studentPracticeService = studentPracticeService;
    }

    // 活动管理接口
    @PostMapping("/activity/add")
    public Result addActivity(@RequestBody SocialPracticeInfoDTO dto) {
        log.info("添加社会实践活动：{}", dto);
        practiceService.addPractice(dto);
        return Result.success();
    }

    @GetMapping("/activity/{practiceId}")
    public Result getActivityById(@PathVariable Integer practiceId) {
        return Result.success(practiceService.getById(practiceId));
    }

    @PutMapping("/activity/update")
    public Result updateActivity(@RequestBody SocialPracticeInfoDTO dto) {
        log.info("更新社会实践活动：{}", dto);
        practiceService.updatePractice(dto);
        return Result.success();
    }

    @DeleteMapping("/activity/delete/{practiceId}")
    public Result deleteActivity(@PathVariable Integer practiceId) {
        log.info("删除社会实践活动：{}", practiceId);
        practiceService.deleteById(practiceId);
        return Result.success();
    }

    @GetMapping("/activity/list")
    public Result getActivityList() {
        return Result.success(practiceService.getAllPractices());
    }

    // 学生参与记录接口
    @PostMapping("/record/add")
    public Result addRecord(@RequestBody StudentPracticeInfoDTO dto) {
        log.info("添加学生实践记录：{}", dto);
        studentPracticeService.addRecord(dto);
        return Result.success();
    }

    @GetMapping("/record/{recordId}")
    public Result getRecordById(@PathVariable Integer recordId) {
        return Result.success(studentPracticeService.getById(recordId));
    }

    @PutMapping("/record/update")
    public Result updateRecord(@RequestBody StudentPracticeInfoDTO dto) {
        log.info("更新学生实践记录：{}", dto);
        studentPracticeService.updateRecord(dto);
        return Result.success();
    }

    @DeleteMapping("/record/delete/{recordId}")
    public Result deleteRecord(@PathVariable Integer recordId) {
        log.info("删除学生实践记录：{}", recordId);
        studentPracticeService.deleteById(recordId);
        return Result.success();
    }

    @PostMapping("/record/page")
    public Result<PageResult> pageRecords(@RequestBody PracticePageQueryDTO dto) {
        log.info("分页查询学生实践记录：{}", dto);
        PageResult pageResult = studentPracticeService.pageQuery(dto);
        return Result.success(pageResult);
    }
}
