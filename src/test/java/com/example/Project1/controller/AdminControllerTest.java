package com.example.Project1.controller;

import com.example.Project1.config.TestConfig;
import com.example.Project1.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.mock;

class AdminControllerTest {

    @Test
    void dependencyInjection() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Object bean = null;

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                bean = context.getBean(beanDefinitionName);
                Assertions.assertThat(bean).isNotNull();
                System.out.println("bean = "+ bean);
            }
        }
    }
    @Test
    void addProduct() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        AdminController adminController = context.getBean(AdminController.class);
        RedirectAttributes mockModel = mock(RedirectAttributes.class);

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