package com.example.Project1.domain.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegRequest {
    private Long id;
    private String name;
    private Integer sellingPrice;
    private Integer supplyingPrice;
    private String description;
    private MultipartFile image;
    private List<MultipartFile> images;
    private Date regDate;
    private Long categoryId;
    private Long deliveryType;
}
