package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.PracticeApplyDTO;
import com.experiment.studentManagement.DTO.RewardApplyDTO;
import com.experiment.studentManagement.Service.*;
import com.experiment.studentManagement.result.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 学生端专用接口 - 学生只能查看自己的数据
 */
@Slf4j
@RestController
@RequestMapping("/api/student-portal")
public class StudentPortalController {

    private final StudentService studentService;
    private final StudentStatusService statusService;
    private final StudentPracticeService practiceService;
    private final RewardPunishmentService rewardPunishmentService;
    private final ScoreService scoreService;
    private final CourseService courseService;
    private final SocialPracticeService socialPracticeService;

    public StudentPortalController(StudentService studentService,
                                   StudentStatusService statusService,
                                   StudentPracticeService practiceService,
                                   RewardPunishmentService rewardPunishmentService,
                                   ScoreService scoreService,
                                   CourseService courseService,
                                   SocialPracticeService socialPracticeService) {
        this.studentService = studentService;
        this.statusService = statusService;
        this.practiceService = practiceService;
        this.rewardPunishmentService = rewardPunishmentService;
        this.scoreService = scoreService;
        this.courseService = courseService;
        this.socialPracticeService = socialPracticeService;
    }

    /**
     * 从JWT中获取studentId
     */
    private Integer getStudentIdFromToken(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("jwtClaims");
        if (claims == null) {
            throw new RuntimeException("未登录");
        }
        String userType = (String) claims.get("userType");
        if (!"student".equals(userType)) {
            throw new RuntimeException("非学生用户无法访问");
        }
        Integer studentId = (Integer) claims.get("studentId");
        if (studentId == null) {
            throw new RuntimeException("学生信息不存在");
        }
        return studentId;
    }

    /**
     * 获取当前学生的基本信息
     */
    @GetMapping("/my-info")
    public Result getMyInfo(HttpServletRequest request) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生查看个人信息：studentId={}", studentId);
        return Result.success(studentService.getById(studentId));
    }

    /**
     * 获取当前学生的学籍信息
     */
    @GetMapping("/my-status")
    public Result getMyStatus(HttpServletRequest request) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生查看学籍信息：studentId={}", studentId);
        return Result.success(statusService.getByStudentId(studentId));
    }

    /**
     * 获取当前学生的社会实践记录
     */
    @GetMapping("/my-practices")
    public Result getMyPractices(HttpServletRequest request) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生查看社会实践：studentId={}", studentId);
        return Result.success(practiceService.getByStudentId(studentId));
    }

    /**
     * 获取当前学生的奖惩记录
     */
    @GetMapping("/my-rewards")
    public Result getMyRewards(HttpServletRequest request) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生查看奖惩记录：studentId={}", studentId);
        return Result.success(rewardPunishmentService.getByStudentId(studentId));
    }

    /**
     * 获取当前学生的成绩记录
     */
    @GetMapping("/my-scores")
    public Result getMyScores(HttpServletRequest request) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生查看成绩记录：studentId={}", studentId);
        return Result.success(scoreService.getByStudentId(studentId));
    }

    /**
     * 获取所有课程（学生可以查看全部课程）
     */
    @GetMapping("/courses")
    public Result getAllCourses(HttpServletRequest request) {
        // 验证是学生用户
        getStudentIdFromToken(request);
        log.info("学生查看课程列表");
        return Result.success(courseService.getAllCourses());
    }

    /**
     * 学生申请社会实践
     */
    @PostMapping("/practice/apply")
    public Result applyPractice(HttpServletRequest request, @RequestBody PracticeApplyDTO dto) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生申请社会实践：studentId={}, practiceId={}", studentId, dto.getPracticeId());
        practiceService.apply(studentId, dto);
        return Result.success();
    }

    /**
     * 学生申请奖励
     */
    @PostMapping("/reward/apply")
    public Result applyReward(HttpServletRequest request, @RequestBody RewardApplyDTO dto) {
        Integer studentId = getStudentIdFromToken(request);
        log.info("学生申请奖励：studentId={}, title={}", studentId, dto.getTitle());
        rewardPunishmentService.apply(studentId, dto);
        return Result.success();
    }

    /**
     * 获取所有社会实践活动（供学生选择申请）
     */
    @GetMapping("/practices/available")
    public Result getAvailablePractices(HttpServletRequest request) {
        getStudentIdFromToken(request);
        log.info("学生查看可申请的社会实践活动");
        return Result.success(socialPracticeService.getAllPractices());
    }
}
