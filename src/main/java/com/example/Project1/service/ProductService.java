package com.example.Project1.service;

import com.example.Project1.dto.ProductDto;
import com.example.Project1.dto.ProductSearchRequest;
import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    int getCount(String type, String keyword);

    List<ProductView> getList(ProductSearchRequest request);

    Optional<String> saveImg(MultipartFile img, String realPath);

    Product getById(Long id);

    void reg(Product product);

    void edit(ProductDto product);

    void deleteAllById(List<Long> deleteId);
}
