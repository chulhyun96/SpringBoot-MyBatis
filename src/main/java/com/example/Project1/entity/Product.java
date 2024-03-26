package com.example.Project1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "admin")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "supply_price", nullable = false)
    private Integer supplyPrice;

    @Column(name = "selling_price", nullable = false)
    private Integer sellingPrice;

    @Column(name = "img")
    private String img;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<DetailImg> detailImgs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Order> orders = new LinkedHashSet<>();

}