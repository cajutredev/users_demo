package com.project.users.service;

import com.project.users.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public String createUser(User user) {
        user.setId(UUID.randomUUID().toString().split("-")[0]);
        users.add(user);
        return user.getId();
    }

    public Optional<User> findById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> updateUser(User user) {
        return users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    return user1;
                });
    }
}
