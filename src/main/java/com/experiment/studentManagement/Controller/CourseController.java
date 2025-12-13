package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.CourseInfoDTO;
import com.experiment.studentManagement.DTO.CoursePageQueryDTO;
import com.experiment.studentManagement.Service.CourseService;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public Result addCourse(@RequestBody CourseInfoDTO dto) {
        log.info("添加课程：{}", dto);
        courseService.addCourse(dto);
        return Result.success();
    }

    @GetMapping("/{courseId}")
    public Result getById(@PathVariable Integer courseId) {
        return Result.success(courseService.getById(courseId));
    }

    @PutMapping("/update")
    public Result update(@RequestBody CourseInfoDTO dto) {
        log.info("更新课程：{}", dto);
        courseService.updateCourse(dto);
        return Result.success();
    }

    @DeleteMapping("/delete/{courseId}")
    public Result delete(@PathVariable Integer courseId) {
        log.info("删除课程：{}", courseId);
        courseService.deleteById(courseId);
        return Result.success();
    }

    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody CoursePageQueryDTO dto) {
        log.info("分页查询课程：{}", dto);
        PageResult pageResult = courseService.pageQuery(dto);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result getList() {
        log.info("查询所有课程");
        return Result.success(courseService.getAllCourses());
    }
}
