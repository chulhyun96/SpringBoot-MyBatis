package com.example.Project1.controller.admin;

import com.example.Project1.domain.dto.request.product.ProductListView;
import com.example.Project1.domain.dto.request.product.ProductRegRequest;
import com.example.Project1.domain.entity.Category;
import com.example.Project1.domain.entity.DeliveryType;
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
import java.util.List;

@RequestMapping("/admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService service;

    @GetMapping("/new")
    public String regForm(Model model) {
        model.addAttribute("product",new ProductRegRequest());
        return "/admin/products/reg";
    }
    @PostMapping("/new")
    public String reg(ProductRegRequest productRegRequest) throws IOException {
        service.reg(productRegRequest);
        log.info("ProductRegRequest = {}", productRegRequest);
        return "redirect:/admin/products";
    }
    @ModelAttribute("categories")
    public List<Category> categories() {
        return service.getCategories();
    }
    @ModelAttribute("deliveryTypes")
    public List<DeliveryType> deliveryCodes() {
        return service.getDeliveryTypes();
    }
    @GetMapping
    public String showList(Model model) {
        List<ProductListView> list = service.getList();
        model.addAttribute("products", list);
        log.info("Product list.size : {}", list.size());
        return "/admin/products/list";
    }
}