package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapping.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductDbService dbService;

    @Autowired
    private ProductMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts() {
        return mapper.mapToProductDtoList(dbService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(@RequestParam Long id) {
        return mapper.mapToProductDto(dbService.getProduct(id).orElseThrow(ProductNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return mapper.mapToProductDto(dbService.saveProduct(mapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return mapper.mapToProductDto(dbService.saveProduct(mapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long id) {
        dbService.deleteProduct(id);
    }
}
