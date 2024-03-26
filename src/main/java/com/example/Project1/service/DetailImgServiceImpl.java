package com.example.Project1.service;

import com.example.Project1.entity.DetailImg;
import com.example.Project1.repository.DetailImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        String[] pathsArr = paths.split(",");
        List<DetailImg> dimgs = new ArrayList<>();

        for (String path : pathsArr) {
            DetailImg dimg = new DetailImg();

            dimg.setPath(path);
            dimg.setProductId(productId);
            dimgs.add(dimg);
        }
        return dimgs;
    }
}
