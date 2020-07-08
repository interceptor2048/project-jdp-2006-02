package com.kodilla.ecommercee.mapping;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private UserMapper userMapper;

    public Order mapToOrder(OrderDto orderDto) throws OrderNotFoundException {
        return new Order(
                orderDto.getStatus(),
                userDbService.getUser(orderDto.getUserDto().getId()).orElseThrow(UserNotFoundException::new));
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getStatus(),
                userMapper.mapToUserDto(order.getUser()));
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getStatus(),
                        userMapper.mapToUserDto(order.getUser())))
                .collect(Collectors.toList());
    }
}
