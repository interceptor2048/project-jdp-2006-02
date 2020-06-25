package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.sun.beans.editors.BooleanEditor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createEmptyCart", consumes = APPLICATION_JSON_VALUE)
    public boolean createEmptyCart(@RequestBody CartDto cart) {
        return cart.isStatus();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getItemFromCart")
    public List<ProductDto> getProductsFromEmptyCart() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addItemToCart", consumes = APPLICATION_JSON_VALUE)
    public ProductDto addProductToCart(ProductDto product) {
        System.out.println("Item: " + product.getName() + " added to cart.");
        return product;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteItemFromCart")
    public boolean deleteProductFromCart(@RequestParam Long id) {
        System.out.println("Item with item_Id  " + id + "deleted from cart");
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public boolean createOrder(OrderDto order) {
        return order.isStatus();
    }
}
