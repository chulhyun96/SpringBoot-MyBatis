package com.example.Project1.service;

import com.example.Project1.entity.DetailImg;
import com.example.Project1.repository.DetailImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailImgServiceImpl implements DetailImgService{
    private final DetailImgRepository repository;
    @Override
    public void regAll(MultipartFile[] subImgs, Long productId) {
        List<DetailImg> detailImgList = new ArrayList<>();
        for (MultipartFile subImg : subImgs) {
            DetailImg detailImageFile = DetailImg.builder()
                    .productId(productId)
                    .path(subImg.getOriginalFilename())
                    .build();
            detailImgList.add(detailImageFile);
        }
        repository.reg(detailImgList);
    }
}
