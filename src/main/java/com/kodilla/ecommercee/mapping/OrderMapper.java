package com.kodilla.ecommercee.mapping;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    //Committed code in waiting for class UserMapper and UserDbService
    //private final UserDbService userDbService;
    //private final UserMapper userMapper; Committed in waiting for UserMapper

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getStatus()
                //userDbService.getUser(orderDto.getUserDto().getId()).orElseThrow(UserNotFoundException::new)
        );
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getStatus()
                //userMapper.mapToUserDto(order.getUser())
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getStatus()))
                //userMapper.mapToUserDto(order.getUser()))
                .collect(Collectors.toList());
    }
}
