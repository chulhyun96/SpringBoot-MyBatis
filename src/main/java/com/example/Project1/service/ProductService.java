package com.example.Project1.service;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getList();
    ProductView getById(Long id);

    void reg(Product product);
}
