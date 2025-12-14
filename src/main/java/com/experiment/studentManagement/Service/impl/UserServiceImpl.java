package com.experiment.studentManagement.Service.impl;

import com.experiment.studentManagement.DTO.userLoginDTO;
import com.experiment.studentManagement.Service.UserService;
import com.experiment.studentManagement.VO.LoginVO;
import com.experiment.studentManagement.mapper.UserMapper;
import com.experiment.studentManagement.model.User;
import com.experiment.studentManagement.properties.JwtProperties;
import com.experiment.studentManagement.utils.JwtUtils;
import com.experiment.studentManagement.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtProperties jwtProperties;

    public UserServiceImpl(UserMapper userMapper, JwtProperties jwtProperties) {
        this.userMapper = userMapper;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void changePassword(Integer userId, com.experiment.studentManagement.DTO.ChangePasswordDTO dto) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!MD5Util.verify(dto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("原密码错误");
        }
        user.setPassword(MD5Util.encrypt(dto.getNewPassword()));
        userMapper.updateById(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateById(user);
    }

    @Override
    public LoginVO login(userLoginDTO dto) {
        User user = userMapper.findByUsername(dto.getUsername());
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        boolean ok = MD5Util.verify(dto.getPassword(), user.getPassword());
        if (!ok) {
            throw new RuntimeException("用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("userType", user.getUserType());
        if (user.getStudentId() != null) {
            claims.put("studentId", user.getStudentId());
        }

        String token = JwtUtils.generateToken(
                claims,
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl()
        );

        LoginVO vo = new LoginVO();
        vo.setToken(token);
        log.info("用户登录成功：{}", token);
        vo.setUserId(user.getUserId());
        vo.setUsername(user.getUsername());
        vo.setRealName(user.getRealName());
        vo.setUserType(user.getUserType());
        vo.setStudentId(user.getStudentId());
        return vo;
    }
}


