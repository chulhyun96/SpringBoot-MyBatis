package com.example.Project1.repository;

import com.example.Project1.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {
    Product save(Product product);
}
