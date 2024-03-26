package com.example.Project1.repository;

import com.example.Project1.entity.DetailImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailImgRepository {
    void reg(List<DetailImg> dimgs);
}
