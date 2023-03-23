package com.project.CakeShop.service.impl;

import com.project.CakeShop.exception.BusinessException;
import com.project.CakeShop.model.Cart;
import com.project.CakeShop.model.OrderedProduct;
import com.project.CakeShop.model.Product;
import com.project.CakeShop.model.User;
import com.project.CakeShop.model.dto.CartDto;
import com.project.CakeShop.model.response.CartProductsByUserIdResponse;
import com.project.CakeShop.repository.CartRepository;
import com.project.CakeShop.repository.OrderedProductRepository;
import com.project.CakeShop.repository.ProductRepository;
import com.project.CakeShop.repository.UserRepository;
import com.project.CakeShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

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

    @Override
    @Transactional
    public Set<OrderedProduct> buyProducts(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            throw new BusinessException(404, "Cart not found!");
        }
        Cart cart = optionalCart.get();
        Set<Product> cartProducts = cart.getCartProducts();
//        cartRepository.deleteAllProductsById(id);
        deleteCartProducts(id);
        Set<OrderedProduct> boughtProducts = new HashSet<>();
        for (Product product : cartProducts) {
            OrderedProduct orderedProduct = new OrderedProduct(product, cart.getUser());
            cart.getUser().getOrderedProducts().add(orderedProduct);
            userRepository.save(cart.getUser());
//             boughtProducts.add(orderedProductRepository.save(orderedProduct));
        }
        return boughtProducts;
    }

    @Override
    public CartProductsByUserIdResponse getCartProductsByUserId(Long id) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(id);
        if (optionalCart.isEmpty()) {
            throw new BusinessException(404, "Cart not found!");
        }
        Set<Product> userProducts = optionalCart.get().getCartProducts();
        Double totalPrice = 0.00;
        for (Product product : userProducts) {
            totalPrice = totalPrice + product.getPrice();
        }
        return new CartProductsByUserIdResponse(userProducts, totalPrice);
    }

    @Transactional
    void deleteCartProducts(Long id) {
        cartRepository.deleteAllProductsById(id);
    }


}
