package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.EmptyCartException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapping.OrderMapper;
import com.kodilla.ecommercee.mapping.ProductMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

    public final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private ProductDbService productDbService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderDbService orderDbService;
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public List<ProductDto> createEmptyCart(@RequestParam Long userId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.getProductList().clear();
        userDbService.createUser(user);
        LOGGER.info("Empty cart was created for user" + userId);
        return productMapper.mapToProductDtoList(user.getProductList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam Long userId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        LOGGER.info("The following products are included in the " + user.getUsername() + "'s cart: " + user.getProductList());
        return productMapper.mapToProductDtoList(user.getProductList());

    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public List<ProductDto> addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Product product = productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
        user.getProductList().add(product);
        userDbService.createUser(user);
        LOGGER.info("Product with id " + productId + " was added to the " + user.getUsername() + "'s cart.");
        return productMapper.mapToProductDtoList(user.getProductList());

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public List<ProductDto> deleteProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Product product = productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
        user.getProductList().remove(product);
        userDbService.createUser(user);
        LOGGER.info("Product with id " + productId + " was removed from the " + user.getUsername() + "'s cart.");
        return productMapper.mapToProductDtoList(user.getProductList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder(@RequestParam Long userId) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        if(user.getProductList().size() == 0) {
            throw new EmptyCartException();
        }
        Order oder = new Order( "New order", user);
        for (Product product: user.getProductList()) {
            oder.getProducts().add(product);
        }
        user.getProductList().clear();
        userDbService.createUser(user);
        orderDbService.saveOrder(oder);
        LOGGER.info("Order for " + user.getUsername() + " was placed on a day " + date + " an hour " + time);
        return orderMapper.mapToOrderDto(oder);

    }
}
