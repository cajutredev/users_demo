package com.project.users.controller;

import com.project.users.entity.User;
import com.project.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/api/users")
    public List<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return userService.getUsers();
    }
}
