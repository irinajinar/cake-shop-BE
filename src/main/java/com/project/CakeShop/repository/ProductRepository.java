package com.project.CakeShop.repository;

import com.project.CakeShop.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);

    Product save(Product product);



}
