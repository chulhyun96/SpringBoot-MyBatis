package com.example.Project1.web.config;

import com.example.Project1.web.login.lntercepter.LogInterceptor;
import com.example.Project1.web.login.lntercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .order(1)
                // 거의 필수
                .excludePathPatterns("/login","/image/**","/css/**","/error","/js/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**")
                .order(2)
                .excludePathPatterns("/login","/image/**","/css/**","/error","/js/**");

    }
}
