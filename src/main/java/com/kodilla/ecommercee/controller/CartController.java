package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.EmptyCartException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapping.OrderMapper;
import com.kodilla.ecommercee.mapping.ProductMapper;
import com.kodilla.ecommercee.mapping.UserMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

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

    @RequestMapping(method = RequestMethod.PUT, value = "createEmptyCart")
    public List<ProductDto> createEmptyCart(@RequestParam Long userId) {
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        user.getProductList().clear();
        userDbService.createUser(user);
        return productMapper.mapToProductDtoList(user.getProductList());
        }

        @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
        public List<ProductDto> getProductsFromCart(@RequestParam Long userId) {
            User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
            return productMapper.mapToProductDtoList(user.getProductList());

        }

        @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
        public List<ProductDto> addProductToCart(@RequestBody Long userId, @RequestParam Long productId) {
            User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
            Product product = productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
            user.getProductList().add(product);
            userDbService.createUser(user);
            return productMapper.mapToProductDtoList(user.getProductList());

        }

        @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
        public List<ProductDto> deleteProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
            User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
            Product product = productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
            user.getProductList().remove(product);
            userDbService.createUser(user);
            return productMapper.mapToProductDtoList(user.getProductList());
        }

        @RequestMapping(method = RequestMethod.POST, value = "createOrder")
        public OrderDto createOrder(@RequestParam Long userId) {

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
            return orderMapper.mapToOrderDto(oder);

        }
    }
