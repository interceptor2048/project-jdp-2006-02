package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long id) {return new UserDto(1L, "TestingJohn", "1", "0");}

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public UserDto createUser(@RequestBody UserDto userDto) {return userDto;}

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public UserDto blockUser(@RequestBody UserDto userDto) {return new UserDto(1L, "TestingJohn", "0", "0");}

    @RequestMapping(method = RequestMethod.PUT, value = "generateUserKey")
    public UserDto generateUserKey(@RequestBody UserDto userDto) {return new UserDto(1L, "TestingJohn", "0", "5");}
}
