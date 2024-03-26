package com.example.Project1.controller.admin;


import com.example.Project1.entity.Category;
import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.service.CategoryService;
import com.example.Project1.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService service;
    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        log.info("ProductController = {}", "ProductController list");
        List<ProductView> list = service.getList();
        model.addAttribute("list",list);
        return "admin/products/list";
    }
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView findById = service.getById(id);
        model.addAttribute("product", findById);
        return "admin/products/detail";
    }
    @PostMapping
    public String reg(@ModelAttribute Product product) {
        return "redirect:/admin/products";
    }
    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> category = categoryService.getList();
        model.addAttribute("category", category);
        return "admin/products/reg";
    }
}

