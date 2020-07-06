package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.service.CartDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartDbService cartDbService;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public CartDto createEmptyCart(@RequestParam Long userId) {
        return cartDbService.createCart(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@RequestBody ProductDto productDto) {
        return cartDbService.addProduct(productDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long productId) {
        cartDbService.deleteProduct(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder(@RequestParam Long userId) {
        return new OrderDto(1L, userId, "status");
    }
}
