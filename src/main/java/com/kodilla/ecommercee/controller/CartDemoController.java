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
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cartdemo")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CartDemoController {

    private final UserDbService userDbService;
    private final ProductDbService productDbService;
    private final OrderDbService orderDbService;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.PUT, value = "emptyCart")
    public List<ProductDto> emptyCart(@RequestParam Long userId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.getProductList().clear();
        userDbService.saveUser(user);
        return productMapper.mapToProductDtoList(user.getProductList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam Long userId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        return productMapper.mapToProductDtoList(user.getProductList());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public List<ProductDto> addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Product product = productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
        user.getProductList().add(product);
        userDbService.saveUser(user);
        return productMapper.mapToProductDtoList(user.getProductList());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "deleteProductFromCart")
    public List<ProductDto> deleteProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Product product = productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
        user.getProductList().remove(product);
        userDbService.saveUser(user);
        return productMapper.mapToProductDtoList(user.getProductList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder(@RequestParam Long userId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        if(user.getProductList().size() == 0) {
            throw new EmptyCartException();
        }
        Order order = new Order(user, "New");
        for(Product product : user.getProductList()) {
            order.getProducts().add(product);
        }
        user.getProductList().clear();
        userDbService.saveUser(user);
        orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(order);
    }
}
