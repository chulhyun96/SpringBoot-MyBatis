package com.example.Project1.controller.admin.mapper;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductMapperTest {
    @Autowired
    private ProductRepository repository;
    @Test
    @DisplayName("ProductMapper findAll Test")
    void mapperTestFindAll() {
        List<ProductView> all = repository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).isExactlyInstanceOf(ProductView.class);
    }
    @Test
    @DisplayName("ProductMapper reg")
    void reg() {
        //에러가 나와야함
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        repository.reg(product);
    }

}
