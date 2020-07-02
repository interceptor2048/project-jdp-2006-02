package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    private Group testGroup;

    @Before
    public void init() {
        Group group = new Group("Group 1");
        groupRepository.save(group);
        testGroup = groupRepository.findById(group.getId()).orElseThrow(GroupNotFoundException::new);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product("Product 1", "Description 1", 10.00, testGroup);
        Product product2 = new Product("Product 2", "Description 2", 20.00, testGroup);
        productRepository.saveAll(Arrays.asList(product1, product2));
        List<Product> productsResult = productRepository.findAll();
        assertTrue(1 < productsResult.size());
        productRepository.deleteAll(Arrays.asList(product1, product2));
    }

    @Test
    public void testGetProduct() {
        Product product = new Product("Product 1", "Description 1", 10.00, testGroup);
        productRepository.save(product);
        Product productResult = productRepository.findById(product.getId()).orElseThrow(ProductNotFoundException::new);
        assertEquals(product.getName(), productResult.getName());
        assertEquals(product.getDescription(), productResult.getDescription());
        assertEquals(product.getPrice(), productResult.getPrice(), 0.0001);
        productRepository.delete(product);
    }

    @After
    public void finalize() {
        groupRepository.delete(testGroup);
    }
}
