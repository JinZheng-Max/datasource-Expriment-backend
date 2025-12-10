package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.ScoreInfoDTO;
import com.experiment.studentManagement.DTO.ScorePageQueryDTO;
import com.experiment.studentManagement.DTO.SemesterDeleteDTO;
import com.experiment.studentManagement.DTO.SemesterInfoDTO;
import com.experiment.studentManagement.Service.ScoreService;
import com.experiment.studentManagement.Service.SemesterService;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score")
@Slf4j
public class ScoreController {

    private final ScoreService scoreService;
    private final SemesterService semesterService;

    public ScoreController(ScoreService scoreService, SemesterService semesterService) {
        this.scoreService = scoreService;
        this.semesterService = semesterService;
    }

    @PostMapping("/add")
    public Result addScore(@RequestBody ScoreInfoDTO dto) {
        log.info("添加成绩：{}", dto);
        scoreService.addScore(dto);
        return Result.success();
    }

    @GetMapping("/{scoreId}")
    public Result getById(@PathVariable Integer scoreId) {
        return Result.success(scoreService.getById(scoreId));
    }

    @PutMapping("/update")
    public Result updateScore(@RequestBody ScoreInfoDTO dto) {
        log.info("更新成绩：{}", dto);
        scoreService.updateScore(dto);
        return Result.success();
    }

    @DeleteMapping("/delete/{scoreId}")
    public Result deleteScore(@PathVariable Integer scoreId) {
        log.info("删除成绩：{}", scoreId);
        scoreService.deleteById(scoreId);
        return Result.success();
    }

    @PostMapping("/page")
    public Result<PageResult> pageScores(@RequestBody ScorePageQueryDTO dto) {
        log.info("分页查询成绩：{}", dto);
        PageResult pageResult = scoreService.pageQuery(dto);
        return Result.success(pageResult);
    }

    // 学期管理接口
    @GetMapping("/semester/list")
    public Result getSemesters() {
        return Result.success(semesterService.getAllSemesters());
    }

    @GetMapping("/semester/current")
    public Result getCurrentSemester() {
        return Result.success(semesterService.getCurrentSemester());
    }

    @GetMapping("/semester/years")
    public Result getAllAcademicYears() {
        return Result.success(semesterService.getAllAcademicYears());
    }

    @PostMapping("/semester/add")
    public Result addSemester(@RequestBody SemesterInfoDTO dto) {
        log.info("添加学期：{}", dto);
        semesterService.addSemester(dto);
        return Result.success();
    }

    @PutMapping("/semester/update")
    public Result updateSemester(@RequestBody SemesterInfoDTO dto) {
        log.info("更新学期：{}", dto);
        semesterService.updateSemester(dto);
        return Result.success();
    }

    @DeleteMapping("/semester/delete/{semesterId}")
    public Result deleteSemester(@PathVariable Integer semesterId) {
        log.info("删除学期：{}", semesterId);
        semesterService.deleteById(semesterId);
        return Result.success();
    }

    @PostMapping("/semester/deleteByYearAndOrder")
    public Result deleteSemesterByYearAndOrder(@RequestBody SemesterDeleteDTO dto) {
        log.info("删除学期及关联数据：学年={}, 学期序号={}", dto.getAcademicYear(), dto.getSemesterOrder());
        try {
            semesterService.deleteSemesterByYearAndOrder(dto);
            return Result.success("删除成功");
        } catch (Exception e) {
            log.error("删除学期失败", e);
            return Result.error(e.getMessage());
        }
    }
}
