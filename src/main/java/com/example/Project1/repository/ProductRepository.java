package com.example.Project1.repository;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductView> findAll();
    ProductView findById(Long id);
    void reg(Product product);
    void updateProductById(Product product);
    void deleteAll(List<Long> deleteId);
}
