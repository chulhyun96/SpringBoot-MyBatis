package com.example.Project1.config;

import com.example.Project1.controller.admin.HomeController;
import com.example.Project1.controller.admin.ProductController;
import com.example.Project1.repository.CategoryRepository;
import com.example.Project1.repository.ProductRepository;
import com.example.Project1.service.CategoryService;
import com.example.Project1.service.CategoryServiceImpl;
import com.example.Project1.service.ProductService;
import com.example.Project1.service.ProductServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public HomeController homeController() {
        return new HomeController();
    }
    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }
    @Bean CategoryRepository categoryRepository() {
        return Mockito.mock(CategoryRepository.class);
    }
    @Bean
    public ProductController productController(ProductService productService, CategoryService categoryService) {
        return new ProductController(productService,categoryService);
    }
    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }
    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }
}

