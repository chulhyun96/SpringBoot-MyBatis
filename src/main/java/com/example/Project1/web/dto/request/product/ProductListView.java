package com.example.Project1.web.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListView {
    private Long id;
    private String name;

    @NumberFormat(pattern = "###,###")
    private Integer sellingPrice;
    @NumberFormat(pattern = "###,###")
    private Integer supplyingPrice;

    private String img;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
}
