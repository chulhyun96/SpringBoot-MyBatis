package com.example.Project1.domain.repository;

import com.example.Project1.domain.dto.request.product.ProductListView;
import com.example.Project1.domain.entity.Category;
import com.example.Project1.domain.entity.DeliveryType;
import com.example.Project1.domain.entity.DetailImg;
import com.example.Project1.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    void saveProduct(Product product);
    void saveSubImg(List<DetailImg> detailImgList);
    Product findById(Long id);
    List<ProductListView> findAll();
    List<Category> findCategories();
    List<DeliveryType> findDeliveryTypes();
}
