package com.example.Project1.service;

import com.example.Project1.entity.Category;
import com.example.Project1.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;

    @Override
    public List<Category> getList() {
        return repository.findAll();
    }
}
