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

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    public Order mapToOrder(OrderDto orderDto) throws UserNotFoundException {
        return new Order(
                orderDto.getName(),
                userDbService.getUser(orderDto.getUserDto().getId()).orElseThrow(UserNotFoundException::new));
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderName(),
                userMapper.mapToUserDto(order.getUser()));
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getOrderName(),
                        userMapper.mapToUserDto(order.getUser())))
                .collect(Collectors.toList());
    }
}
