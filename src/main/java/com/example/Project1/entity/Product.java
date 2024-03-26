package com.example.Project1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "supplying_price", nullable = false)
    private Integer supplyingPrice;

    @Column(name = "selling_price", nullable = false)
    private Integer sellingPrice;

    @Column(name = "img")
    private String img;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    private Long categoryId;

}