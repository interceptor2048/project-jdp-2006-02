package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductOrderDto {

    private final Long id;
    private final Long productId;
    private final Long orderId;
}
