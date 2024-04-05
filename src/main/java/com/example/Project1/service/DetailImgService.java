package com.example.Project1.service;

import org.springframework.web.multipart.MultipartFile;

public interface DetailImgService {
    void regAll(MultipartFile[] subImgs, Long productId);
}
