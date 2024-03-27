package com.example.Project1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailImg {
    private Integer id;
    private String path;
    private Long productId;
}