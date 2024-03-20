package com.example.Project1.service;

import com.example.Project1.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> getList();
}
