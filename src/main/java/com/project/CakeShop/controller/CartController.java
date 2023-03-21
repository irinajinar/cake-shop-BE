package com.project.CakeShop.controller;

import com.project.CakeShop.model.Cart;
import com.project.CakeShop.model.dto.CartDto;
import com.project.CakeShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/product")
    public ResponseEntity<Void> addProductToCart(@RequestBody CartDto cartDto) {
        cartService.addProductToCart(cartDto);
        return ResponseEntity.ok().build();
    }

}
