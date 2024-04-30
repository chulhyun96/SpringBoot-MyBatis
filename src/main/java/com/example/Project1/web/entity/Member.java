package com.example.Project1.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer id;
    private String userId;
    private String password;
    private LocalDate regDate;
}