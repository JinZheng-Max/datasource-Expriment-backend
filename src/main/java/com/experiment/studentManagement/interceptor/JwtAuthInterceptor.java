package com.experiment.studentManagement.interceptor;

import com.experiment.studentManagement.properties.JwtProperties;
import com.experiment.studentManagement.result.Result;
import com.experiment.studentManagement.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;

//拦截器
@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;
    private final ObjectMapper objectMapper;

    public JwtAuthInterceptor(JwtProperties jwtProperties, ObjectMapper objectMapper) {
        this.jwtProperties = jwtProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenHeaderName = jwtProperties.getUserTokenName();
        String token = request.getHeader(tokenHeaderName);
        if (token == null || token.isEmpty()) {
            writeUnauthorized(response, "未提供令牌");
            return false;
        }
        try {
            Claims claims = JwtUtils.parseToken(token, jwtProperties.getUserSecretKey());
            request.setAttribute("jwtClaims", claims);
            return true;
        } catch (Exception e) {
            writeUnauthorized(response, "令牌无效或已过期");
            return false;
        }
    }

    private void writeUnauthorized(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");
        String body = objectMapper.writeValueAsString(Result.error(message));
        response.getWriter().write(body);
        response.getWriter().flush();
    }
}


