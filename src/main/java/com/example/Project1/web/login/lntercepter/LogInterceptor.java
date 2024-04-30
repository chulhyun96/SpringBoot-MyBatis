package com.example.Project1.web.login.lntercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //요청 url 로그 찍기
        String requestURI = request.getRequestURI();
        MDC.put("RequestURI", requestURI);

        //요청자 로그 찍기
        HttpSession session = request.getSession(false);
        if (session != null) {
            String memberId = (String) session.getAttribute("loginMember");
            if (memberId != null) {
                MDC.put("Member", memberId);
            }
        }
        //요청을 처리할 핸들러 로그 찍기
        if (handler instanceof HandlerMethod) {
            MDC.put("HandlerMethod", String.valueOf(handler));
            // MDC.put("HandlerMethod", String.valueOf(handler))
        }
        log.info("[ Intercepter PreHandle ] = {} {} {}", MDC.get("RequestURI"), MDC.get("Member"), MDC.get("HandlerMethod"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 메서드 호출 후, 실행되는 메서드 반환된 ModelAndView 정보를 알 수 있음
        log.info("[ Intercepter PostHandle ] = {}", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //예외 로그, 종료 로그, 응답 로그 찍기
        if (handler instanceof HandlerMethod) {
            MDC.put("handler", String.valueOf(handler));
        }
        log.info("[ Intercepter AfterCompletion]] = {} {} {}", MDC.get("RequestURI"), MDC.get("Member"),MDC.get("handler"));

        // ex가 null이 아니라면 예외 발생
        if (ex != null) {
            log.error("[ Exception ]", ex);
        }
        MDC.remove("RequestURI");
        MDC.remove("Member");
        MDC.remove("HandlerMethod");
    }
}
