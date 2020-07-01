package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartDto;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDbService {

    @Autowired
    private CartRepository cartRepository;

    public CartDto createCart() {
        return cartRepository.save();
    }

    public CartDto addProduct(ProductDto productDto) {
        return cartRepository.saveProduct(productDto);
    }
}
