package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserDbService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {return userRepository.save(user);}

    public Optional<User> getUser(Long id) {return userRepository.findById(id);}

    public User blockUser(User user) {
        user.setStatus("block");
        return user;
    }

    public User generateKey(User user) {
        Random random = new Random();
        int key = random.nextInt(89999) + 10000;
        user.setUserKey(key);
        return user;
    }
}
