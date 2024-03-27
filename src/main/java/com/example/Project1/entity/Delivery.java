package com.example.Project1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Delivery {
    private Integer id;
    private Byte status;

}