package com.example.Project1.entity;

import com.example.Project1.dto.ProductDto;
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

    public Product updateFromRequest(ProductDto updateProduct) {
        this.name = updateProduct.getName();
        this.supplyingPrice = updateProduct.getSupplyingPrice();
        this.sellingPrice = updateProduct.getSellingPrice();
        this.img = updateProduct.getImg();
        this.description = updateProduct.getDescription();
        this.categoryId = updateProduct.getCategoryId();
        return this;
    }
}