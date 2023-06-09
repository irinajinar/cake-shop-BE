package com.project.CakeShop.controller;

import com.project.CakeShop.model.Product;
import com.project.CakeShop.model.dto.ProductDto;
import com.project.CakeShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        Product product = productService.addProduct(productDto);
        return ResponseEntity.ok(product);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        System.out.println(id);
        Product product = productService.update(id, productDto);
        return ResponseEntity.ok(product);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProducts() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        System.out.println(id);
        productService.delete(id);

    }

}
