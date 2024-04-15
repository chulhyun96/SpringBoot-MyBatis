package com.example.Project1.controller.admin;

import com.example.Project1.domain.dto.request.product.ProductRegRequest;
import com.example.Project1.domain.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService service;

    @GetMapping("/new")
    public String regProductForm(Model model) {
        model.addAttribute("product",new ProductRegRequest());
        return "/admin/products/reg";
    }
    @PostMapping("/new")
    public String regProduct(@ModelAttribute ProductRegRequest productRegRequest) throws IOException {
        service.reg(productRegRequest);
        return "redirect:/admin/products/new";
    }
}