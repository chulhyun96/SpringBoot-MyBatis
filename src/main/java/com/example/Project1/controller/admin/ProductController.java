package com.example.Project1.controller.admin;

import com.example.Project1.domain.dto.request.product.ProductListView;
import com.example.Project1.domain.dto.request.product.ProductRequest;
import com.example.Project1.domain.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RequestMapping("/admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService service;

    @GetMapping("/new")
    public String regForm(Model model) {
        model.addAttribute("product",new ProductRequest());
        model.addAttribute("categories", service.getCategories());
        model.addAttribute("deliveryTypes", service.getDeliveryTypes());
        return "/admin/products/reg";
    }
    @PostMapping("/new")
    public String reg(@Validated ProductRequest regRequest, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("Reg Form Error : {}", bindingResult + "\n");
            return "redirect:/admin/products/new";
        }

        service.reg(regRequest);
        log.info("ProductRegRequest = {}", regRequest);
        return "redirect:/admin/products";
    }
    @GetMapping("/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        //이게 ProductRequest여야 MultipartFile을 받을 수 있음
        ProductRequest findProduct = service.getProductById(id);
        model.addAttribute("product", findProduct);
        model.addAttribute("categories", service.getCategories());
        model.addAttribute("deliveryTypes", service.getDeliveryTypes());
        log.info("UpdateForm ProductRegRequest = {}", findProduct);
        return "/admin/products/detail";
    }
    @PostMapping("/{id}/update")
    public String update(ProductRequest updateRequest) throws IOException {
        log.info("Update Method ProductUpdateRequest = {}", updateRequest);
        // update에서 수정해야함
        service.update(updateRequest);
        return "redirect:/admin/products";
    }

    @GetMapping
    public String showList(Model model) {
        List<ProductListView> list = service.getList();
        model.addAttribute("products", list);
        log.info("Product list.size : {}", list.size());
        return "/admin/products/list";
    }
}