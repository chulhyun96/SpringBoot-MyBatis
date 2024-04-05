package com.example.Project1.service;

import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<ProductView> getList() {
        return repository.findAll(null, null);
    }

    @Override
    public List<ProductView> getList(String type, String keyword) {
        return repository.findAll(type, keyword);
    }

    @Override
    public ProductView getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void reg(Product product) {
        repository.reg(product);
    }

    @Override
    public void edit(Product product) {
        repository.updateProductById(product);
    }

    @Override
    public void deleteAllById(List<Long> deleteId) {
        repository.deleteAll(deleteId);
    }

    @Override
    public Optional<String> saveImg(MultipartFile img, String realPath) {
        String fileName = img.getOriginalFilename();

        if (img != null && !img.isEmpty()) {

            File pathFile = new File(realPath); // 인자로 들어온 경로를 기준으로 디렉토리 생성 있다면 넘어감
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File file = new File(realPath + File.separator + fileName);
            try {
                img.transferTo(file); // MultipartFile의 내용을 특정 경로에 저장하기 위함
                return Optional.ofNullable(fileName);
            } catch (IOException e) {
                log.error("[File Object Error] = {}", e.getMessage());
            }
        }
        return Optional.empty();
    }
}
