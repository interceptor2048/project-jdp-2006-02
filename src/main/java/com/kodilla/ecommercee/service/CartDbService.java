package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CartDbService {

    @Autowired
    CartRepository cartRepository;

    public CartDto createCart(Long userId) {
        return cartRepository.save(userId);
    }

    public CartDto addProduct(ProductDto productDto) {
       return cartRepository.saveProduct(productDto);
    }

    public void deleteProduct(Long productId) {
        cartRepository.deleteById(productId);
    }

}
