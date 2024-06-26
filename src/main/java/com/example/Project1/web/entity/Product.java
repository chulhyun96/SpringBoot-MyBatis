package com.example.Project1.web.entity;

import com.example.Project1.web.dto.request.img.UploadImg;
import com.example.Project1.web.dto.request.product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Integer supplyingPrice;
    private Integer sellingPrice;
    private String img;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    private Long categoryId;
    private Long deliveryType;

    public static Product toEntity(ProductRequest request, UploadImg uploadImg) {
        return Product.builder()
                .name(request.getName())
                .sellingPrice(request.getSellingPrice())
                .supplyingPrice(request.getSupplyingPrice())
                .description(request.getDescription())
                .img(uploadImg.getStorageName())
                .categoryId(request.getCategoryId())
                .deliveryType(request.getDeliveryType())
                .build();
    }

    public Product update(ProductRequest updateRequest, UploadImg updateImg) {
        return Product.builder()
                .id(updateRequest.getId())
                .name(updateRequest.getName())
                .supplyingPrice(updateRequest.getSupplyingPrice())
                .sellingPrice(updateRequest.getSellingPrice())
                .img(updateImg.getStorageName())
                .description(updateRequest.getDescription())
                .categoryId(updateRequest.getCategoryId())
                .deliveryType(updateRequest.getDeliveryType())
                .build();
    }

    public UploadImg getCurrentImg(String path) {
        return UploadImg.builder().storageName(path).build();
    }
}