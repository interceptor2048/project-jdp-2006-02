package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class CartDto {
    private final Long id;
    private final Long userId;
    private final List<ProductDto> products = new ArrayList<>();

}
