package com.example.Project1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "product_view", schema = "admin")
public class ProductView {
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "selling_price", nullable = false)
    private Integer sellingPrice;

    @Column(name = "supplying_price", nullable = false)
    private Integer supplyingPrice;

    @Column(name = "img")
    private String img;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "category_name", length = 500)
    private String categoryName;

    @Column(name = "di_id", nullable = false)
    private Integer diId;

    @Column(name = "path", length = 5000)
    private String path;

    @Column(name = "di_product_id", nullable = false)
    private Integer diProductId;

}