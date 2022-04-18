package com.example.clevertecspringshop.service;

import com.example.clevertecspringshop.entity.Product;
import com.example.clevertecspringshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record ProductService(ProductRepository productRepository) {

    public Optional<Product> findByProductId(Integer id) {
        return productRepository.findById(id);
    }
}
