package com.example.Project1.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;

}