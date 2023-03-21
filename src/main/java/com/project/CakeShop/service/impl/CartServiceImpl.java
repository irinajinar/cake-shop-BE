package com.project.CakeShop.service.impl;

import com.project.CakeShop.exception.BusinessException;
import com.project.CakeShop.model.Cart;
import com.project.CakeShop.model.Product;
import com.project.CakeShop.model.User;
import com.project.CakeShop.model.dto.CartDto;
import com.project.CakeShop.repository.CartRepository;
import com.project.CakeShop.repository.ProductRepository;
import com.project.CakeShop.repository.UserRepository;
import com.project.CakeShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addCart(CartDto cartDto) {
        if (Objects.isNull(cartDto)) {
            throw new BusinessException(400, "The cart is empty!");
        }

        if (Objects.isNull(cartDto.getUserId())) {
            throw new BusinessException(400, "The user id is invalid!");
        }
        Cart cart = new Cart();
        Optional<User> optionalUser = userRepository.findById(cartDto.getUserId());
        if (optionalUser.isEmpty()) {
            throw new BusinessException(404, "User not found");
        }
        if (cartRepository.existsByUserId(cartDto.getUserId())) {
            throw new BusinessException(400, "This user already has a cart");
        }
        cart.setUser(optionalUser.get());
        return cartRepository.save(cart);

    }

    @Override
    @Transactional
    public Cart addProductToCart(CartDto cartDto) {
        Optional<Product> optionalProduct = productRepository.findById(cartDto.getProductId());
        if (optionalProduct.isEmpty()) {
            throw new BusinessException(404, "Product not found");
        }
        Optional<Cart> optionalCart = cartRepository.findByUserId(cartDto.getUserId());
        if (optionalCart.isEmpty()) {
            throw new BusinessException(404, "Cart not found");
        }
        Cart cart = optionalCart.get();
        Set<Product> cartProducts = cart.getCartProducts();
        if (cartProducts.contains(optionalProduct.get())) {
            throw new BusinessException(400, "The product was already added to cart");
        }
        cartProducts.add(optionalProduct.get());
        Product product = optionalProduct.get();
        product.setQuantity(product.getQuantity() - 1);
        cart.setCartProducts(cartProducts);
        return cartRepository.save(cart);
    }
}
