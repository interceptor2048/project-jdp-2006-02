package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private List<ProductDto> products = new ArrayList<>();


    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto createEmptyCart() {
        return new CartDto(1L, 1L, new ArrayList<>());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@RequestBody ProductDto product) {
        products.add(product);
        return new CartDto(1L, 1L, products);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public boolean deleteProductFromCart(@RequestParam Long productId) {
        products.remove(productId);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(final CartDto cartDto) {
        return new OrderDto(1L, cartDto.getUserId(), cartDto.getId() );
    }
}
