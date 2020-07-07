package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapping.ProductMapper;
import com.kodilla.ecommercee.mapping.UserMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.aspectj.weaver.ast.Or;
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
    private ProductDbService productDbService;

    @Autowired
    private OrderDbService orderDbService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart")
    public CartDto createEmptyCart(@RequestParam Long userId) {
        return new CartDto(1L, userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getProductsFromCart() {
        return productMapper.mapToProductDtoList(productDbService.getAllProducts());

    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public List<Product> addProductToCart(@RequestBody ProductDto productDto) {
        List<Product> products = new ArrayList<>();
        products.add(productMapper.mapToProduct(productDto));
        return products;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long productId) {
        productDbService.deleteProduct(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public OrderDto createOrder(@RequestParam Long userId) throws UserNotFoundException {
//       return orderDbService.saveOrder(new Order(1L, userDbService.getUser(userId).orElseThrow(UserNotFoundException::new), "done"));
//       return orderDbService.saveOrder(new Order(1L, userMapper.mapToUser(userMapper.mapToUserDto(userDbService.getUser(userId))), "done"));


        return new OrderDto(1L, userId, "status");
    }
}
