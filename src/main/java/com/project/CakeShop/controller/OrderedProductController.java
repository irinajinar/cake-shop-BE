package com.project.CakeShop.controller;

import com.project.CakeShop.model.OrderedProduct;
import com.project.CakeShop.service.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/ordered-products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderedProductController {

    @Autowired
    private OrderedProductService orderedProductService;

    @GetMapping
    public ResponseEntity<ArrayList<OrderedProduct>> showAllOrderedProducts(){
        ArrayList<OrderedProduct> orderedProducts= orderedProductService.showAllOrderedProducts();
        return ResponseEntity.ok(orderedProducts);
    }



}
