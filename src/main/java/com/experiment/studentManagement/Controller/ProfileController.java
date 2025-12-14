package com.experiment.studentManagement.Controller;

import com.experiment.studentManagement.DTO.ChangePasswordDTO;
import com.experiment.studentManagement.Service.StudentService;
import com.experiment.studentManagement.Service.UserService;
import com.experiment.studentManagement.VO.ProfileVO;
import com.experiment.studentManagement.model.User;
import com.experiment.studentManagement.result.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 个人信息管理接口 - 学生端和管理端共用
 */
@Slf4j
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;
    private final StudentService studentService;

    public ProfileController(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    /**
     * 从JWT中获取用户信息
     */
    private Claims getClaimsFromToken(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("jwtClaims");
        if (claims == null) {
            throw new RuntimeException("未登录");
        }
        return claims;
    }

    /**
     * 获取当前用户的个人信息
     */
    @GetMapping("/info")
    public Result getProfileInfo(HttpServletRequest request) {
        Claims claims = getClaimsFromToken(request);
        Integer userId = (Integer) claims.get("userId");
        String userType = (String) claims.get("userType");
        
        log.info("获取个人信息：userId={}, userType={}", userId, userType);
        
        ProfileVO vo = new ProfileVO();
        User user = userService.getUserById(userId);
        if (user != null) {
            vo.setUserId(user.getUserId());
            vo.setUsername(user.getUsername());
            vo.setRealName(user.getRealName());
            vo.setUserType(user.getUserType());
        }
        
        // 如果是学生，获取学生详细信息
        if ("student".equals(userType)) {
            Integer studentId = (Integer) claims.get("studentId");
            if (studentId != null) {
                vo.setStudentInfo(studentService.getById(studentId));
            }
        }
        
        return Result.success(vo);
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public Result changePassword(HttpServletRequest request, @RequestBody ChangePasswordDTO dto) {
        Claims claims = getClaimsFromToken(request);
        Integer userId = (Integer) claims.get("userId");
        
        log.info("修改密码：userId={}", userId);
        userService.changePassword(userId, dto);
        return Result.success();
    }
}
