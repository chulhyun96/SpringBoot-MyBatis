package com.example.Project1.repository;

import com.example.Project1.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    Product save(Product product);

    List<Product> findAll();
}
