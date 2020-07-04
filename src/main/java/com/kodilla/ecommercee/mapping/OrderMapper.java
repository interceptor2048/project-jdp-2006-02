package com.kodilla.ecommercee.mapping;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderMapper {

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getUser().getId(), order.getStatus());
    }
}
