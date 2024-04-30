package com.example.Project1.web.login;

import com.example.Project1.web.entity.Member;
import com.example.Project1.web.service.member.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService service;
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String login(@Validated LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request) {
        log.info("Login Try");
        if (bindingResult.hasErrors()) {
            log.info("LoginErrors = {}", bindingResult);
            return "login/loginForm";
        }
        Member loginMember = service.login(loginForm.getLoginId(), loginForm.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail","적절치 못한 사용자");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember.getUserId());
        log.info("Login Success");
        return "redirect:/admin/products";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        }
        session.invalidate();;
        return "redirect:/login";
    }
}
