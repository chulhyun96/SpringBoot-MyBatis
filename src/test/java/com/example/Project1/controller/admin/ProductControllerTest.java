package com.example.Project1.controller.admin;

import com.example.Project1.config.TestConfig;
import com.example.Project1.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.mock;

public class ProductControllerTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
    ProductController controller = context.getBean(ProductController.class);

    private Product product;

    @BeforeEach
    void setUp() {
        product = createTestProduct();
    }
    private Product createTestProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setSupplyPrice(30000);
        product.setSellingPrice(30000);
        product.setImg("testImgPath");
        product.setDescription("testDescription");
        return product;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void regProduct() {


        // when
        controller.reg(product);
        // then
        Assertions.assertThat(product.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("리스트 출력 테스트")
    void listProduct() {
        // given
        Model model = mock(Model.class);
        // when
        List<Product> list = List.of(product);
        String viewPath = controller.list(model);
        // then
        Assertions.assertThat(list.size()).isEqualTo(1);
        Assertions.assertThat(viewPath).isEqualTo("admin/products/list");
    }

    @Test
    @DisplayName("상품 상세페이지 테스트")
    void getProductById() {
        // when
        Model model = mock(Model.class);
        String detailPath = controller.detail(product.getId(), model);
        // then
        Assertions.assertThat(product.getId()).isEqualTo(1);
        Assertions.assertThat(detailPath).isEqualTo("admin/products/detail");
    }
}
