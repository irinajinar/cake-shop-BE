package com.project.CakeShop.service.impl;

import com.project.CakeShop.exception.BusinessException;
import com.project.CakeShop.model.Product;
import com.project.CakeShop.model.dto.ProductDto;
import com.project.CakeShop.repository.ProductRepository;
import com.project.CakeShop.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(ProductDto productDto) {
        if (Objects.isNull(productDto)) {
            throw new BusinessException(401, "Body is null");
        }
        if (Objects.isNull(productDto.getName())) {
            throw new BusinessException(400, "The name is not valid!");
        }
        if (!Objects.isNull(productRepository.findByName(productDto.getName()))) {
            throw new BusinessException(401, "The product name is taken");
        }
        if (Objects.isNull(productDto.getPrice()) && (productDto.getPrice() < 0)) {
            throw new BusinessException(400, "The price is not valid!");
        }
        if (Objects.isNull(productDto.getQuantity()) && (productDto.getQuantity() < 0)) {
            throw new BusinessException(400, "Quantity is not valid!");
        }
        Product product = new Product();
        product.setProductType(productDto.getProductType());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);

    }

    @Override
    public Product update(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new BusinessException(401, "Body is null");
        }
        if (Objects.isNull(productDto.getName())) {
            throw new BusinessException(400, "The name is not valid!");
        }

        if (Objects.isNull((productDto.getPrice())) && (productDto.getPrice() < 0)) {
            throw new BusinessException(400, "The price is not valid!");
        }
        if (Objects.isNull(productDto.getQuantity()) && (productDto.getQuantity() < 0)) {
            throw new BusinessException(400, "Quantity is not valid!");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setProductType(productDto.getProductType());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new BusinessException(401, "Product not find!");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new BusinessException(401, "Product not find!");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
}
