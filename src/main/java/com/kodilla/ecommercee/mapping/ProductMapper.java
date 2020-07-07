package com.kodilla.ecommercee.mapping;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private GroupDbService groupDbService;

    public Product mapToProduct(ProductDto productDto) {
        Group group = groupDbService.getGroup(productDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(),
                productDto.getPrice(), group);
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getGroup().getId());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
