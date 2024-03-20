package com.example.Project1.controller.admin;


import com.example.Project1.entity.Product;
import com.example.Project1.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @GetMapping
    public String list(Model model) {
        log.info("ProductController = {}", "ProductController list");
        List<Product> list = service.getList();
        model.addAttribute("list",list);
        return "admin/products/list";
    }
}
