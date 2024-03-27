package com.example.Project1.controller.admin;


import com.example.Project1.entity.Category;
import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.service.CategoryService;
import com.example.Project1.service.DetailImgService;
import com.example.Project1.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    private final CategoryService categoryService;

    private final DetailImgService detailImgService;

    @GetMapping
    public String list(Model model) {
        List<ProductView> list = service.getList();
        model.addAttribute("list", list);
        return "admin/products/list";

    }

    @PutMapping
    public String edit(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        Gson gson = new Gson();
        return "redirect:/admin/products";
    }
    @GetMapping("{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return "admin/products/detail";
    }

    @PostMapping
    public String reg(@ModelAttribute Product product, Long categoryId, String paths) {
        product.setCategoryId(categoryId);
        service.reg(product);

        detailImgService.regAll(paths, product.getId());
        return "redirect:/admin/products";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return "admin/products/reg";
    }
}


