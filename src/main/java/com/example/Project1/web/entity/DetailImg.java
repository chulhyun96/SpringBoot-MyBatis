package com.example.Project1.web.entity;

import com.example.Project1.web.dto.request.img.UploadImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailImg {
    private Integer id;
    private String path;
    private Long productId;


    public static List<DetailImg> from(List<UploadImg> subImgs, Product product) {
        return subImgs.stream()
                .map(img -> DetailImg.builder()
                        .productId(product.getId())
                        .path(img.getStorageName())
                        .build())
                .toList();
    }

    public static List<DetailImg> updateOf(List<UploadImg> subImgs, List<DetailImg> detailImgs) {
        return IntStream.range(0, subImgs.size())
                .mapToObj(i -> {
                    DetailImg detailImg;
                    if (i < detailImgs.size()) {
                        detailImg = detailImgs.get(i);
                        detailImg.setPath(subImgs.get(i).getStorageName());
                    } else {
                        detailImg = DetailImg.builder()
                                .path(subImgs.get(i).getStorageName())
                                .build();
                    }
                    return detailImg;
                })
                .collect(Collectors.toList());
    }
}