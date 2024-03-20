package com.example.Project1.controller;

import com.example.Project1.config.TestConfig;
import com.example.Project1.controller.admin.HomeController;
import com.example.Project1.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.mock;

class HomeControllerTest {

    @Test
    void dependencyInjection() {
        // given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        // when
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == beanDefinition.ROLE_APPLICATION) {
                Object bean = context.getBean(beanDefinitionName);
                // then
                Assertions.assertThat(bean).isNotNull();
            }
        }
    }

    @Test
    void addProduct() {
        // given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        HomeController homeController = context.getBean(HomeController.class);
        RedirectAttributes mockRa = mock(RedirectAttributes.class);

        // when
        Product product = new Product();
        product.setName("test");
        product.setSupplyPrice(30000);
        product.setSellingPrice(30000);
        product.setImg("testImgPath");
        product.setDescription("testDescription");
        System.out.println("product = " + product);

        homeController.addProduct(product, mockRa);

        // then
        Assertions.assertThat(product.getName()).isEqualTo("test");

    }
}