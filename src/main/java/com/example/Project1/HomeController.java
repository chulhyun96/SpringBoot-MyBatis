package com.example.Project1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@SessionAttribute(name = "loginMember", required = false) String loginMember, Model model) {
        //비회원 or 세션이 만료되었다면
        if (loginMember == null) {
            return "/";
        }
        model.addAttribute("loginMember", loginMember);
        return "admin/products/list";
    }
}
