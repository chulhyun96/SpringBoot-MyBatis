package com.example.Project1.service;

import com.example.Project1.entity.Product;
import com.example.Project1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }
    @Override
    public List<Product> getList() {
        return repository.findAll();
    }
}
