package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.RewardPunishmentInfoDTO;
import com.experiment.studentManagement.DTO.RewardPunishmentPageQueryDTO;
import com.experiment.studentManagement.Service.RewardPunishmentService;
import com.experiment.studentManagement.result.PageResult;
import com.experiment.studentManagement.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reward")
@Slf4j
public class RewardPunishmentController {

    private final RewardPunishmentService rpService;

    public RewardPunishmentController(RewardPunishmentService rpService) {
        this.rpService = rpService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody RewardPunishmentInfoDTO dto) {
        log.info("添加奖惩记录：{}", dto);
        rpService.add(dto);
        return Result.success();
    }

    @GetMapping("/{rpId}")
    public Result getById(@PathVariable Integer rpId) {
        return Result.success(rpService.getById(rpId));
    }

    @PutMapping("/update")
    public Result update(@RequestBody RewardPunishmentInfoDTO dto) {
        log.info("更新奖惩记录：{}", dto);
        rpService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/delete/{rpId}")
    public Result delete(@PathVariable Integer rpId) {
        log.info("删除奖惩记录：{}", rpId);
        rpService.deleteById(rpId);
        return Result.success();
    }

    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody RewardPunishmentPageQueryDTO dto) {
        log.info("分页查询奖惩记录：{}", dto);
        PageResult pageResult = rpService.pageQuery(dto);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result getList() {
        log.info("查询所有奖惩记录");
        return Result.success(rpService.getAll());
    }
}
