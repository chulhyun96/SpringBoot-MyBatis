package com.example.Project1.domain.dto.request.img;

import lombok.Data;

@Data
public class UploadImg {
    private String originalName;
    private String storageName;

    public UploadImg(String originalName, String storageName) {
        this.originalName = originalName;
        this.storageName = storageName;
    }
}
