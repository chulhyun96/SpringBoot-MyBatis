package com.example.Project1.controller;


import com.example.Project1.entity.Product;
import com.example.Project1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;

    @PostMapping("/product")
    public String addProduct(@ModelAttribute Product productToSave, Model model) {
        model.addAttribute("product", productService.save(productToSave));
        return "redirect:/index";
    }
}
