package com.project.CakeShop.model.response;

import com.project.CakeShop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CartProductsByUserIdResponse {
    private Set<Product> productList;
    private Double totalPrice;
}
