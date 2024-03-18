package com.example.Project1.controller;

import com.example.Project1.config.TestConfig;
import com.example.Project1.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AdminControllerTest {
    @Test
    void addProduct() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        AdminController adminController = ac.getBean("adminController", AdminController.class);
        Product product = new Product();
        product.setName("test");
        product.setSupplyPrice(30000);
        product.setSellingPrice(30000);
        product.setImg("testImgPath");
        product.setDescription("testDescription");

        adminController.addProduct(product);
        Assertions.assertThat(product.getName()).isEqualTo("test");
    }
}