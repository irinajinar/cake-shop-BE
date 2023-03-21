package com.project.CakeShop.service;

import com.project.CakeShop.model.Cart;
import com.project.CakeShop.model.dto.CartDto;

public interface CartService {

    Cart addCart (CartDto cartDto);

    Cart addProductToCart(CartDto cartDto);
}
