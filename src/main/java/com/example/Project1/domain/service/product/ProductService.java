package com.example.Project1.domain.service.product;

import com.example.Project1.domain.dto.request.img.UploadImg;
import com.example.Project1.domain.dto.request.product.ProductListView;
import com.example.Project1.domain.dto.request.product.ProductRegRequest;
import com.example.Project1.domain.entity.Category;
import com.example.Project1.domain.entity.DeliveryType;
import com.example.Project1.domain.entity.DetailImg;
import com.example.Project1.domain.entity.Product;
import com.example.Project1.domain.repository.ProductRepository;
import com.example.Project1.domain.service.product.img.ImgStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ImgStore imgStore;

    @Transactional
    public void reg(ProductRegRequest productRegRequest) throws IOException {
        //Product
        UploadImg uploadImg = imgStore.storeMainImg(productRegRequest.getImage());
        Product newProduct = Product.toEntity(productRegRequest, uploadImg);
        repository.saveProduct(newProduct);

        //DetailImg
        List<UploadImg> uploadImgs = imgStore.storeSubImgs(productRegRequest.getImages());
        List<DetailImg> detailImgList = new ArrayList<>();
        for (UploadImg img : uploadImgs) {
            detailImgList.add(DetailImg.builder().
                    path(img.getStorageName()).
                    productId(newProduct.getId()).
                    build());
        }
        repository.saveSubImg(detailImgList);
    }

    public void update(ProductRegRequest productRegRequest) {
        Product foundProduct = repository.findById(productRegRequest.getId());
        log.info("foundProduct = {}", foundProduct);
    }

    public List<ProductListView> getList() {
        return repository.findAll();
    }
    public List<Category> getCategories() {
        return repository.findCategories();
    }

    public List<DeliveryType> getDeliveryTypes() {
        return repository.findDeliveryTypes();
    }
}
