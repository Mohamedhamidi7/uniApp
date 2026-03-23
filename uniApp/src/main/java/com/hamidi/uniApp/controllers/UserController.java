package com.hamidi.uniApp.controllers;

import com.hamidi.uniApp.entities.User;
import com.hamidi.uniApp.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public User register(@RequestBody User user){
        return userService.createUser(user);
    }
}
