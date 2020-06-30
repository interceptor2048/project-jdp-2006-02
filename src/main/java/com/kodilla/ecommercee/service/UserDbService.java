package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDbService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {return userRepository.save(user);}

    public Optional<User> getUser(Long id) {return userRepository.findById(id);}
}
