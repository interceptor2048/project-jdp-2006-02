package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    //ProductRepository productRepository;

    @Test
    public void testSaveUser() {
        //Given
        User user = new User("Johny");

        //When
        userRepository.save(user);

        //Then
        Long id = user.getId();
        Optional<User> isJohny = userRepository.findById(id);
        Assert.assertTrue(isJohny.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testFindUserById() {

        //Given
        User user = new User("Jack");
        userRepository.save(user);
        Long id = user.getId();

        //When
        Optional<User> isJack = userRepository.findById(id);

        //Then
        Assert.assertEquals("Jack", isJack.get().getUsername());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testBlockUser() {

        //Given
        User user = new User("Jack", "active");
        userRepository.save(user);
        Long id = user.getId();

        //When
        Optional<User> blockTestUser = userRepository.findById(id);
        blockTestUser.get().setStatus("block");

        //Then
        Assert.assertEquals("block", blockTestUser.get().getStatus());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testGenerateUserKey() {

        //Given
        User user = new User("Jack", "active", "0");
        userRepository.save(user);
        Long id = user.getId();

        //When
        Optional<User> generateUserKeyTestUser = userRepository.findById(id);
        generateUserKeyTestUser.get().setUserKey("12345");

        //Then
        Assert.assertNotEquals("0", generateUserKeyTestUser.get().getUserKey());
        Assert.assertEquals("12345", generateUserKeyTestUser.get().getUserKey());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testSaveProductList() {

        //Given
        User user = new User("Jack");
        User user2 = new User("John");
        Product product = new Product("Egg");
        Product product1 = new Product("Onion");
        Product product2 = new Product("Sausage");

        userRepository.save(user);
        userRepository.save(user2);

        //productRepository.save(product);
        //productRepository.save(product1);
        //productRepository.save(product2);

        //When

        //Then

        //ClenUp
    }
}
