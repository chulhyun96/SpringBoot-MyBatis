package com.example.Project1.controller.admin;


import com.example.Project1.entity.Product;
import com.example.Project1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public String list(Model model) {
        List<Product> list = service.getList();
        return "admin/products/list";
    }
}
