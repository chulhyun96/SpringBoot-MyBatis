package com.example.Project1.domain.service.product;

import com.example.Project1.domain.dto.request.img.UploadImg;
import com.example.Project1.domain.dto.request.product.ProductListView;
import com.example.Project1.domain.dto.request.product.ProductRequest;
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
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ImgStore imgStore;

    @Transactional
    public void reg(ProductRequest regRequest) throws IOException {
        //Product
        UploadImg uploadImg = imgStore.storeMainImg(regRequest.getImage());
        Product newProduct = Product.toEntity(regRequest, uploadImg);
        repository.saveProduct(newProduct);

        //DetailImg
        List<DetailImg> detailImgList = imgStore.storeSubImgs(regRequest.getImages()).stream()
                .map(img -> DetailImg.builder()
                        .path(img.getStorageName())
                        .productId(newProduct.getId())
                        .build())
                .collect(Collectors.toList());
        repository.saveSubImg(detailImgList);

    }

    public void update(ProductRequest updateRequest) throws IOException {
        log.info("See Img updateRequest = {}", updateRequest.getImage());
        Product foundProduct = repository.findById(updateRequest.getId());
        UploadImg updateImg = imgStore.storeMainImg(updateRequest.getImage());

        Product updatedProduct = foundProduct.update(updateRequest,updateImg);
        repository.updateProduct(updatedProduct);

        List<DetailImg> uploadImgs = imgStore.storeSubImgs(updateRequest.getImages()).stream()
                .map(img -> DetailImg.builder()
                        .productId(updatedProduct.getId())
                        .path(img.getStorageName())
                        .build())
                        .collect(Collectors.toList());
        repository.updateSubImgs(uploadImgs);

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

    public ProductRequest getProductById(Long id) {
        Product foundProduct = repository.findById(id);
        log.info("See RegDate found product : {}", foundProduct);
        return ProductRequest.toRequest(foundProduct);
    }
}
