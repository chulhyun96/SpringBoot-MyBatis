package com.example.Project1.domain.dto.request.product;

import com.example.Project1.domain.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer sellingPrice;

    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    private Integer supplyingPrice;

    @NotBlank
    private String description;

    @NotNull
    private MultipartFile image;

    @NotNull
    private List<MultipartFile> images;

    private String regDate;

    @NotNull
    private Long categoryId;

    @NotNull
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

    public int getImagesSize() {
        return images.size();
    }
}
