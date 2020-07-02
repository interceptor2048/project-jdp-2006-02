package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityOrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveManyToMany() {
        //Given
        Product bike = new Product();
        Product notebook = new Product();
        Product jeans = new Product();

        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();

        order1.getProducts().add(bike);
        order2.getProducts().add(notebook);
        order3.getProducts().add(jeans);

        //When
        orderRepository.save(order1);
        Long order1Id = order1.getId();

        orderRepository.save(order2);
        Long order2Id = order2.getId();

        orderRepository.save(order3);
        Long order3Id = order3.getId();

        //Then
        Assert.assertNotEquals(0, java.util.Optional.of(order1Id));
        Assert.assertNotEquals(0, java.util.Optional.of(order2Id));
        Assert.assertNotEquals(0, java.util.Optional.of(order3Id));

        //CleanUp
        orderRepository.deleteById(order1Id);
        orderRepository.deleteById(order2Id);
        orderRepository.deleteById(order3Id);
    }
}
