package com.project.users.service;

import com.project.users.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public List<User> createUser(User user) {
        users.add(user);
        return users;
    }
}
