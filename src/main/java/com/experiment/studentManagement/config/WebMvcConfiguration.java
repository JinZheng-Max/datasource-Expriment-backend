package com.experiment.studentManagement.config;


import com.experiment.studentManagement.interceptor.JwtAuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;



/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebMvcConfiguration(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/user/login",
                        "/error",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/static/**",
                        "/resources/**",
                        "/public/**"
                );
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/springdoc-ui/",
                        "classpath:/META-INF/resources/"
                );

        registry.addResourceHandler("/v3/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "classpath:/static/",
                        "classpath:/public/",
                        "classpath:/resources/",
                        "classpath:/META-INF/resources/"
                );
    }
}
