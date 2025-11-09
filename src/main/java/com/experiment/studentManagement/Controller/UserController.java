package com.experiment.studentManagement.Controller;

import lombok.extern.slf4j.Slf4j;
import com.experiment.studentManagement.DTO.userLoginDTO;
import com.experiment.studentManagement.Service.UserService;
import com.experiment.studentManagement.VO.LoginVO;
import com.experiment.studentManagement.result.Result;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody userLoginDTO dto) {
        log.info("用户登录：{}", dto);
        LoginVO vo = userService.login(dto);
        return Result.success(vo);
    }
}


