package com.project.CakeShop.repository;

import com.project.CakeShop.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart save(Cart cart);

    Boolean existsByUserId(Long userId);

    Optional<Cart> findByUserId(Long userId);

}
