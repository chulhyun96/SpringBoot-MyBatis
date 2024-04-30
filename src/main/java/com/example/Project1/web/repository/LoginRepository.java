package com.example.Project1.web.repository;

import com.example.Project1.web.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface LoginRepository {
    Optional<Member> findByLoginId(String loginId);
}
