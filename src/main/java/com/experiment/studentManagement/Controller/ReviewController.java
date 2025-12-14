package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.PracticePageQueryDTO;
import com.experiment.studentManagement.DTO.ReviewDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentPageQueryDTO;
import com.experiment.studentManagement.Service.RewardPunishmentService;
import com.experiment.studentManagement.Service.StudentPracticeService;
import com.experiment.studentManagement.result.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员审核接口
 */
@Slf4j
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final StudentPracticeService practiceService;
    private final RewardPunishmentService rewardPunishmentService;

    public ReviewController(StudentPracticeService practiceService,
                           RewardPunishmentService rewardPunishmentService) {
        this.practiceService = practiceService;
        this.rewardPunishmentService = rewardPunishmentService;
    }

    /**
     * 从JWT中获取用户ID
     */
    private Integer getUserIdFromToken(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("jwtClaims");
        if (claims == null) {
            throw new RuntimeException("未登录");
        }
        String userType = (String) claims.get("userType");
        if (!"admin".equals(userType)) {
            throw new RuntimeException("无权限操作");
        }
        return (Integer) claims.get("userId");
    }

    /**
     * 获取待审核的社会实践列表
     */
    @GetMapping("/practice/pending")
    public Result getPendingPractices(HttpServletRequest request, PracticePageQueryDTO dto) {
        getUserIdFromToken(request);
        log.info("管理员查看待审核社会实践列表");
        return Result.success(practiceService.getPendingList(dto));
    }

    /**
     * 审核社会实践申请
     */
    @PostMapping("/practice/review")
    public Result reviewPractice(HttpServletRequest request, @RequestBody ReviewDTO dto) {
        Integer reviewerId = getUserIdFromToken(request);
        log.info("管理员审核社会实践：reviewerId={}, recordId={}, status={}", reviewerId, dto.getId(), dto.getStatus());
        practiceService.review(reviewerId, dto);
        return Result.success();
    }

    /**
     * 获取待审核的奖励申请列表
     */
    @GetMapping("/reward/pending")
    public Result getPendingRewards(HttpServletRequest request, RewardPunishmentPageQueryDTO dto) {
        getUserIdFromToken(request);
        log.info("管理员查看待审核奖励列表");
        return Result.success(rewardPunishmentService.getPendingList(dto));
    }

    /**
     * 审核奖励申请
     */
    @PostMapping("/reward/review")
    public Result reviewReward(HttpServletRequest request, @RequestBody ReviewDTO dto) {
        Integer reviewerId = getUserIdFromToken(request);
        log.info("管理员审核奖励申请：reviewerId={}, rpId={}, status={}", reviewerId, dto.getId(), dto.getStatus());
        rewardPunishmentService.review(reviewerId, dto);
        return Result.success();
    }
}
