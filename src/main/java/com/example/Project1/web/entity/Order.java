package com.example.Project1.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private LocalDate regDate;
    private Member member;
    private Product product;

}