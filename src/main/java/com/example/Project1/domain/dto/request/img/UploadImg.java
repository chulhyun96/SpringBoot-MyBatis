package com.example.Project1.domain.dto.request.img;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadImg {
    private String originalName;
    private String storageName;

}
