package com.example.Project1.domain.entity;

import com.example.Project1.domain.dto.request.img.UploadImg;
import com.example.Project1.domain.dto.request.product.ProductRegRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDate regDate;
    private Long categoryId;

    public static Product toEntity(ProductRegRequest request, UploadImg uploadImg) {
        return Product.builder()
                .name(request.getName())
                .sellingPrice(request.getSellingPrice())
                .supplyingPrice(request.getSupplyingPrice())
                .description(request.getDescription())
                .img(uploadImg.getStorageName())
                .categoryId(request.getCategoryId())
                .build();
    }
}