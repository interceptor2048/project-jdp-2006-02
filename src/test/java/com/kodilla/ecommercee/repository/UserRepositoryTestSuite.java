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

    @Test
    public void testSaveUser() {
        //Given
        User user = new User("Jack", "active", "0");

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
        User user = new User("Jack", "active", "0");
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
        User user = new User("Jack", "active", "0");
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
}
