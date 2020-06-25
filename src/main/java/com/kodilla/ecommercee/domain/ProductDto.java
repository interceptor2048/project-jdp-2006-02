package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private final Long id;
    private final String name;
    private final String description;
    private final double price;
    private final Long groupId;
}