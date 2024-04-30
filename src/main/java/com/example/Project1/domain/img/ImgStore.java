package com.example.Project1.domain.img;

import com.example.Project1.web.dto.request.img.UploadImg;
import com.example.Project1.web.entity.DetailImg;
import com.example.Project1.web.entity.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "file")
public class ImgStore {
    private String mainImgDir;
    private String subImgDir;

    public Optional<List<UploadImg>> storeSubImgs(List<MultipartFile> imgFiles, List<DetailImg> foundImgs) {
        if (imgFiles.stream().allMatch(MultipartFile::isEmpty)) {
            List<UploadImg> uploadImgs = new ArrayList<>();
            for (DetailImg detailImg : foundImgs ) {
                String path = detailImg.getPath();
                uploadImgs.add(UploadImg.builder().storageName(path).build());
            }
            return Optional.of(uploadImgs);
        }
        return Optional.of(imgFiles.stream()
                .map(subImgFile -> {
                    String originalImgName = subImgFile.getOriginalFilename();
                    String storeImgName = createStoreImgName(originalImgName);
                    try {
                        for (DetailImg foundImg : foundImgs) {
                            Path oldPath = Paths.get(getFullSubImgPath(foundImg.getPath()));
                            deleteFileIfExist(oldPath);
                        }
                        String fullSubImgPath = getFullSubImgPath(originalImgName);
                        createDirIfNonExist(fullSubImgPath);
                        subImgFile.transferTo(new File(fullSubImgPath));
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                    return new UploadImg(originalImgName, storeImgName);
                }).collect(Collectors.toList()));
    }

    public Optional<UploadImg> storeMainImg(MultipartFile imgFile, Product product) throws IOException {
        if (imgFile.isEmpty()) {
            return Optional.ofNullable(product.getCurrentImg(product.getImg()));
        }
        String originalImgName = imgFile.getOriginalFilename();

        if (product != null) {
            Path oldPath = Paths.get(getFullMainImgPath(product.getImg()));
            deleteFileIfExist(oldPath);
        }

        String storeImgName = createStoreImgName(originalImgName);
        String fullMainImgPath = getFullMainImgPath(storeImgName);
        createDirIfNonExist(fullMainImgPath);

        imgFile.transferTo(new File(fullMainImgPath));
        return Optional.of(new UploadImg(originalImgName, storeImgName));
    }

    private static void deleteFileIfExist(Path oldPath) throws IOException {
        if (Files.exists(oldPath)) {
            Files.delete(oldPath);
        }
    }


    private String getFullMainImgPath(String fileName) {
        return System.getProperty("user.dir") + mainImgDir + fileName;
    }

    private String getFullSubImgPath(String fileName) {
        return System.getProperty("user.dir") + subImgDir + fileName;
    }

    private void createDirIfNonExist(String storeImgName) throws IOException {
        Path path = Paths.get(storeImgName);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private String createStoreImgName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
