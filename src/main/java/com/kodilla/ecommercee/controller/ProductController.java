package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDto> getProducts() {
        return Arrays.asList(
                new ProductDto(1L, "Test product 1", "Test description 1", 10.00, 1L),
                new ProductDto(2L, "Test product 2", "Test description 2", 20.00, 2L));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return new ProductDto(id, "Test product", "Test description", 10.00, 1L);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteProduct(@PathVariable Long id) {
    }
}
