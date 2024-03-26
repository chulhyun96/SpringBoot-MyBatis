package com.example.Project1.service;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public List<ProductView> getList() {
        return repository.findAll();
    }

    @Override
    public ProductView getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void reg(Product product) {
        repository.reg(product);
    }
}
