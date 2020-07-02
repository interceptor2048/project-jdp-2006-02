package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testOrderSave() {
        //Given
        Order order = new Order();
        //When
        orderRepository.save(order);
        List<Order> orders = orderRepository.findAll();
        //Then
        assertTrue(orders.size() > 0);
        //CleanUp
        orderRepository.delete(order);
    }

    @Test
    public void testOrderFindAll() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        //When
        List<Order> orders = orderRepository.findAll();
        //Then
        assertEquals(3, orders.size());
        //CleanUp
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        orderRepository.delete(order3);
    }

    @Test
    public void testOrderFindById() {
        //Given
        User user = new User();
        List<Product> products = new ArrayList<>();
        Order order = new Order(1L, user, products, "done");
        orderRepository.save(order);
        //When
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        Order orderResult = optionalOrder.orElse(new Order(2L, user, products, "open"));
        //Then
        assertEquals(order.getStatus(), orderResult.getStatus());
        //CleanUp
        orderRepository.delete(order);
    }
}
