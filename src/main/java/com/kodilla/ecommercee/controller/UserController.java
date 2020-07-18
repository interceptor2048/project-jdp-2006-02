
package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapping.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    public final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDbService userDbService;

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long id) throws UserNotFoundException {
        LOGGER.info("The following user " + id + " has been selected.");
        return userMapper.mapToUserDto(userDbService.getUser(id).orElseThrow(UserNotFoundException::new));}

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        LOGGER.info("The user " + userDto.getUsername() + " was successfully created.");
        return userMapper.mapToUserDto(userDbService.createUser(userMapper.mapToUser(userDto)));}

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public UserDto blockUser(@RequestParam Long id) throws UserNotFoundException {
        LOGGER.info("The following user " + id + " has been blocked.");
        return userMapper.mapToUserDto(userDbService.blockUser(userDbService.getUser(id).orElseThrow(UserNotFoundException::new)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateUserKey")
    public UserDto generateUserKey(@RequestParam Long id) throws UserNotFoundException {
        LOGGER.info("User key has been created correctly.");
        return userMapper.mapToUserDto(userDbService.generateKey(userDbService.getUser(id).orElseThrow(UserNotFoundException::new)));
    }
}
