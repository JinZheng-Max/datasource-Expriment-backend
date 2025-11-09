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
        return vo;
    }
}


