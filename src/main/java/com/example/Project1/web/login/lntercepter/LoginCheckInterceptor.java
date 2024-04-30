package com.example.Project1.web.login.lntercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("사용자 인증 체크");
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("비회원 접근 시도 : [{}]", requestURI);
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
