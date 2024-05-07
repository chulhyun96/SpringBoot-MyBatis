package com.example.Project1.web.dto.request.product;

import com.example.Project1.web.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;

    @NotBlank
    private String name;

    @NotNull(message = "값을 입력해주세요.")
    @Range(min = 50, max = 1_000_000)
    @NumberFormat(pattern = "###,###")
    private Integer sellingPrice;

    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    @NumberFormat(pattern = "###,###")
    private Integer supplyingPrice;

    @NotBlank
    private String description;

    @NotNull
    private MultipartFile image;

    @NotNull
    private List<MultipartFile> images;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

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
