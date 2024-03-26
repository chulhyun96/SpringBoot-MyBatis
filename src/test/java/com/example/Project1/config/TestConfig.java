package com.example.Project1.config;

import com.example.Project1.controller.admin.HomeController;
import com.example.Project1.controller.admin.ProductController;
import com.example.Project1.repository.ProductRepository;
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
    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }
    @Bean
    public ProductController productController(ProductService productService) {
        return new ProductController(productService);
    }
}

