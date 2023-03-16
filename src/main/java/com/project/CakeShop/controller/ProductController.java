package com.project.CakeShop.controller;

import com.project.CakeShop.model.Product;
import com.project.CakeShop.model.dto.ProductDto;
import com.project.CakeShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
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

    @GetMapping(path="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product= productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        System.out.println(id);
        productService.delete(id);

    }

}
