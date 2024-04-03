package com.example.Project1.service;

import com.example.Project1.entity.DetailImg;
import com.example.Project1.repository.DetailImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailImgServiceImpl implements DetailImgService{
    private final DetailImgRepository repository;
    @Override
    public void regAll(String paths, Long productId) {
        List<DetailImg> dimgs = splitPath(paths, productId);
        repository.reg(dimgs);
    }
    private List<DetailImg> splitPath(String paths, Long productId) {
        return Arrays.stream(paths.split(","))
                .map(path -> DetailImg.
                        builder().
                        path(path).
                        productId(productId).
                        build())
                .collect(Collectors.toList());
    }
}
