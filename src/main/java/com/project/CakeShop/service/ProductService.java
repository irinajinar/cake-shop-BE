package com.project.CakeShop.service;

import com.project.CakeShop.model.Product;
import com.project.CakeShop.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDto productDto);
    Product update(Long id, ProductDto productDto);
    void delete(Long id);
    Product getProduct(Long id);
    List<Product> getProducts();

}
