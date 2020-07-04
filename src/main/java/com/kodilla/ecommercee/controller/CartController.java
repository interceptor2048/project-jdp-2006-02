package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapping.OrderMapper;
import com.kodilla.ecommercee.mapping.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

<<<<<<< HEAD
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartDbService cartDbService;
    @Autowired
    private ProductDbService productDbService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDbService orderDbService;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto createEmptyCart() {
        return cartDbService.createCart();
=======
    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public CartDto createEmptyCart(@RequestParam Long userId) {
        return new CartDto(1L, userId);
>>>>>>> master
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart() {
        return productMapper.mapToProductList(productDbService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@RequestBody ProductDto productDto) {
        return cartDbService.addProduct(productDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long productId) {
        LOGGER.debug("The product was removed from the cart");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder(@RequestParam Long userId) {
        return orderMapper.mapToOrderDto(orderDbService.getOrder(userId).orElseThrow(UserNotFoundException::new));

    }
}
