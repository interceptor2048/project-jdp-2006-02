package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CardDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductOrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {


    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart", consumes = APPLICATION_JSON_VALUE)
    public CardDto createEmptyCart() {
        return new CardDto(1L, null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CardDto addProductToCart(@RequestBody ProductDto product) {
        return new CardDto(1L, product.getId());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public boolean deleteProductFromCart(@RequestParam Long productId) {
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public ProductOrderDto createOrder(@RequestBody final CardDto cardDto) {
        return new ProductOrderDto(cardDto.getProduct_id(), cardDto.getUser_id());
    }
}
