package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapping.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderDbService orderDbService;

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderDbService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long id) {
        return orderMapper.mapToOrderDto(orderDbService.getOrder(id).orElseThrow(OrderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam Long id) {
        if (orderDbService.getOrder(id).isPresent()) {
            orderDbService.deleteOrder(id);
        } else {
            throw new OrderNotFoundException();
        }
    }
}
