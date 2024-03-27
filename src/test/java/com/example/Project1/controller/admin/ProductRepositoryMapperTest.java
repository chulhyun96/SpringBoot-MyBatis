package com.example.Project1.controller.admin;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryMapperTest {
    @Autowired
    ProductRepository repository;

    @Test
    @DisplayName("상품 등록 테스트")
    void regProductTest() {
        //given
        Product product1 = new Product();
        product1.setName("test");
        product1.setSellingPrice(50000);
        product1.setSupplyingPrice(50000);

        Product product2 = new Product();
        //when
        repository.reg(product1);

        //then
        Assertions.assertThat(product1.getId()).isGreaterThan(0);
        Assertions.assertThat(product1).isNotNull();

        //Product2 query failed
        assertThrows(DataIntegrityViolationException.class, () -> repository.reg(product2));
    }

    @Test
    @DisplayName("리스트 출력 테스트")
    void listProduct() throws Exception {
        List<ProductView> list = repository.findAll();

        Assertions.assertThat(list).isNotEmpty();
        Assertions.assertThat(list.size()).isGreaterThan(0);
    }
    @Test
    @DisplayName("상품 디테일 테스트")
    void getProductById() {

    }

    @Test
    @DisplayName("카테고리 불러오기 테스트")
    void getCategoryList() {
    }

}

