package com.example.Project1.controller.admin;

import com.example.Project1.config.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class HomeControllerTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("의존성 주입 테스트")
    void dependencyInjection() {
        // given
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
    @DisplayName("HomeController index 페이지")
    void homeControllerTest() {
        // given
        HomeController bean = context.getBean(HomeController.class);
        // when
        String index = bean.index();
        // then
        Assertions.assertThat(index).isEqualTo("admin/index");
    }
}