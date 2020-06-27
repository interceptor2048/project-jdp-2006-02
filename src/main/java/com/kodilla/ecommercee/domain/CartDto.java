package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class CartDto {
    private final Long id;
    private final Long user_id;
    private final Long product_id;

}
