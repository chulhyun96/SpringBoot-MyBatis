package com.example.Project1.web.repository;

import com.example.Project1.web.dto.request.product.ProductListView;
import com.example.Project1.web.entity.Category;
import com.example.Project1.web.entity.DeliveryType;
import com.example.Project1.web.entity.DetailImg;
import com.example.Project1.web.entity.Product;
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

    void updateProduct(Product updatedProduct);

    void updateSubImgs(List<DetailImg> uploadImgs);

    List<DetailImg> findImgs(Long id);

    void deleteSubImgs(List<DetailImg> deleteImgs);
}
