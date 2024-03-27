package com.example.Project1.entity;

import jakarta.persistence.*;
import lombok.*;

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