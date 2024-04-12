package com.example.Project1.dto;

import com.example.Project1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer supplyingPrice;
    private Integer sellingPrice;
    private String img;
    private String description;
    private LocalDate regDate;
    private Long categoryId;

    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .supplyingPrice(this.supplyingPrice)
                .sellingPrice(this.sellingPrice)
                .img(this.img)
                .description(this.description)
                .build();
    }

    public ProductDto update(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .supplyingPrice(product.getSupplyingPrice())
                .sellingPrice(product.getSellingPrice())
                .img(product.getImg())
                .description(product.getDescription())
                .build();
    }
}
