package com.example.Project1.domain.repository;

import com.example.Project1.domain.entity.DetailImg;
import com.example.Project1.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    void saveProduct(Product product);
    void saveSubImg(List<DetailImg> detailImgList);
}
