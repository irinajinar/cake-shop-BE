package com.project.CakeShop.service;

import com.project.CakeShop.model.Cart;
import com.project.CakeShop.model.OrderedProduct;
import com.project.CakeShop.model.dto.CartDto;
import com.project.CakeShop.model.response.CartProductsByUserIdResponse;

import java.util.Set;

public interface CartService {

    Cart addCart (CartDto cartDto);

    Cart addProductToCart(CartDto cartDto);


    Set<OrderedProduct> buyProducts(Long id);

    CartProductsByUserIdResponse getCartProductsByUserId(Long id);
}
