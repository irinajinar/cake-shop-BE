package com.project.CakeShop.repository;

import com.project.CakeShop.model.OrderedProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderedProductRepository extends CrudRepository<OrderedProduct, Long> {

}
