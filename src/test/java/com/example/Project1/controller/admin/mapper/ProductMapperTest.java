package com.example.Project1.controller.admin.mapper;

import com.example.Project1.entity.ProductView;
import com.example.Project1.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductMapperTest {
    @Autowired
    private ProductRepository repository;
    @Test
    public void testFindAll() {
        ProductView byId = repository.findById(1L);
        System.out.println(byId.getId());
        assertThat(byId).isNotNull();
        assertThat(byId.getId()).isEqualTo(1);
    }
}
