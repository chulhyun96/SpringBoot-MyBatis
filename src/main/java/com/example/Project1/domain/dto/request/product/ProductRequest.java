package com.example.Project1.domain.dto.request.product;

import com.example.Project1.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Long id;
    private String name;
    private Integer sellingPrice;
    private Integer supplyingPrice;
    private String description;
    private MultipartFile image;
    private List<MultipartFile> images;
    private String regDate;
    private Long categoryId;
    private Long deliveryType;


    public static ProductRequest toRequest(Product foundProduct) {
        return ProductRequest.builder()
                .id(foundProduct.getId())
                .name(foundProduct.getName())
                .sellingPrice(foundProduct.getSellingPrice())
                .supplyingPrice(foundProduct.getSellingPrice())
                .description(foundProduct.getDescription())
                .regDate(foundProduct.getRegDate())
                .categoryId(foundProduct.getCategoryId())
                .deliveryType(foundProduct.getDeliveryType())
                .build();
    }
}
