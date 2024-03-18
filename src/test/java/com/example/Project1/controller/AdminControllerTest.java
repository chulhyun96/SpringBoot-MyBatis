package com.example.Project1.controller;

import com.example.Project1.config.TestConfig;
import com.example.Project1.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;

class AdminControllerTest {
    @Test
    void addProduct() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        AdminController adminController = context.getBean(AdminController.class);
        Model mockModel = mock(Model.class);

        // given
        Product product = new Product();
        product.setName("test");
        product.setSupplyPrice(30000);
        product.setSellingPrice(30000);
        product.setImg("testImgPath");
        product.setDescription("testDescription");
        System.out.println("product = " + product);

        // when
        adminController.addProduct(product,mockModel);

        // then
        Assertions.assertThat(product.getName()).isEqualTo("test");


    }
}