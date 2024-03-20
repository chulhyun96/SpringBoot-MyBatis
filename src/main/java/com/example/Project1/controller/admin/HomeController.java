package com.example.Project1.controller.admin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminHomeController")
@RequestMapping("/admin")
@Slf4j
public class HomeController {
    @GetMapping
    public String index() {
        log.info("HomeController = {}", "HomeController/index");
        return "admin/index";
    }
}

