package com.example.Project1.domain.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListView {
    private Long id;
    private String name;
    private String sellingPrice;
    private String supplyingPrice;
    private String img;
    private Date regDate;
}
