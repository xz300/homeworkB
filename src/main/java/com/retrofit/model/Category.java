package com.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Category implements Serializable {
    private Long id;
    private String title;
    private List<ProductDto> products;

}

