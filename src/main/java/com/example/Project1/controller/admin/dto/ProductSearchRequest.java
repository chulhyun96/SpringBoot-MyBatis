package com.example.Project1.controller.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {

    private String type;

    private String keyword = "";

    private Integer page = 1;

    public String getKeyword() {
        return keyword.trim();
    }

}
