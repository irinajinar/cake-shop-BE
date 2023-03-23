package com.project.CakeShop.service.impl;

import com.project.CakeShop.model.OrderedProduct;
import com.project.CakeShop.repository.CartRepository;
import com.project.CakeShop.repository.OrderedProductRepository;
import com.project.CakeShop.repository.ProductRepository;
import com.project.CakeShop.service.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ArrayList<OrderedProduct> showAllOrderedProducts() {
        return (ArrayList<OrderedProduct>) orderedProductRepository.findAll();
    }



}
