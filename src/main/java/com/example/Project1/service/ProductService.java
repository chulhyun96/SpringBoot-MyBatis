package com.example.Project1.service;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<ProductView> getList();
    List<ProductView> getList(String type, String keyword);
    ProductView getById(Long id);
    void reg(Product product);
    void edit(Product product);
    void deleteAllById(List<Long> deleteId);
    Optional<String> saveImg(MultipartFile img, String realPath);
}
