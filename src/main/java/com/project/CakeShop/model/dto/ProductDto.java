package com.project.CakeShop.model.dto;

import com.project.CakeShop.model.ProductType;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private ProductType productType;

    private String name;

    private Double price;

    private Integer quantity;
}
