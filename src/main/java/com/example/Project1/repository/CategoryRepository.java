package com.example.Project1.repository;

import com.example.Project1.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {
    List<Category> findAll();
}
