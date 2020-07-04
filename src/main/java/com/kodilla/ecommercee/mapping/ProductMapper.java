package com.kodilla.ecommercee.mapping;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), new Group());
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getGroup().getId());
    }

    public List<ProductDto> mapToProductList(List<Product> productList) {
        return productList.stream()
                .map(product -> new ProductDto(product.getId(), product.getName(),product.getDescription(), product.getPrice(), product.getGroup().getId()))
                .collect(Collectors.toList());

    }
}
