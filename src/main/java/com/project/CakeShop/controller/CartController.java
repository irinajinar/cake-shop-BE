package com.project.CakeShop.controller;

import com.project.CakeShop.model.OrderedProduct;
import com.project.CakeShop.model.dto.CartDto;
import com.project.CakeShop.model.response.CartProductsByUserIdResponse;
import com.project.CakeShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/product")
    public ResponseEntity<Void> addProductToCart(@RequestBody CartDto cartDto) {
        cartService.addProductToCart(cartDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value= "/{id}/buy")
    public ResponseEntity<Set<OrderedProduct>> buyProducts(@PathVariable Long id){
        Set<OrderedProduct> orderProducts=cartService.buyProducts(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/products/user/{id}")
    public ResponseEntity<CartProductsByUserIdResponse> getCartProductsByUserId(@PathVariable Long id){
        CartProductsByUserIdResponse cartProductsByUserIdResponse=cartService.getCartProductsByUserId(id);
        return ResponseEntity.ok(cartProductsByUserIdResponse);
    }



}
