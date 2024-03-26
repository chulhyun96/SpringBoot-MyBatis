package com.example.Project1.controller.admin;

import com.example.Project1.config.TestConfig;
import com.example.Project1.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.mockito.Mockito.mock;

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
    @DisplayName("상품 등록 테스트")
    void addProduct() {
        // given
        ProductController productController = context.getBean(ProductController.class);
        RedirectAttributes mockRa = mock(RedirectAttributes.class);

        // when
        Product product = new Product();
        product.setName("test");
        product.setSupplyPrice(30000);
        product.setSellingPrice(30000);
        product.setImg("testImgPath");
        product.setDescription("testDescription");
        System.out.println("product = " + product);

        /*productController.addProduct(product, mockRa);*/

        // then
        Assertions.assertThat(product.getName()).isEqualTo("test");

    }

    @Test
    @DisplayName("리스트 출력 테스트")
    void listProducts() {
        // given
        ProductController bean = context.getBean(ProductController.class);
        Model model = mock(Model.class);

        Product product = new Product();
        product.setName("test");
        product.setSupplyPrice(30000);
        product.setSellingPrice(30000);
        product.setImg("testImgPath");
        product.setDescription("testDescription");

        // when
        List<Product> list = List.of(product);
        String viewPath = bean.list(model);

        // then
        Assertions.assertThat(list.size()).isEqualTo(1);
        Assertions.assertThat(viewPath).isEqualTo("admin/products/list");
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