package com.example.Project1.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    @NotBlank(message = "{error.product.name}")
    private String name;
    private Integer supplyingPrice;
    private Integer sellingPrice;
    private String imgPath;
    private String description;
    private LocalDate regDate;
    private Long categoryId;
}