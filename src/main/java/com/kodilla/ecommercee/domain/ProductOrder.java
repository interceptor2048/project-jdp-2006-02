package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductOrder {

    private final Long id;
    private final Long productId;
    private final Long orderId;
}
