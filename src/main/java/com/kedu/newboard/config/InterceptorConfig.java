package com.kedu.newboard.config;

import com.kedu.newboard.interceptors.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer { // JWT interceptor 설정

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("인터셉트 동작 확인");
        registry.addInterceptor(jwtInterceptor)
        .addPathPatterns("/**") // 모든 요청 필터링
        .excludePathPatterns("/auth"); // 허용해줄 url 필터링
    }
}
