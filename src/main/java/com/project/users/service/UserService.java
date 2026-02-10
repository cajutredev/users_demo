package com.project.users.service;

import com.project.users.entity.User;
import com.project.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private List<User> users = new ArrayList<User>();
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
//        user.setId(UUID.randomUUID().toString().split("-")[0]);
//        users.add(user);
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
//        return users.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst();
        return userRepository.findById(id);
    }

    public Boolean updateUser(User user) {
//        return users.stream()
//                .filter(u -> u.getId().equals(user.getId()))
//                .findFirst()
//                .map(user1 -> {
//                    user1.setFirstName(user.getFirstName());
//                    user1.setLastName(user.getLastName());
//                    return user1;
//                });
        return userRepository.findById(user.getId())
                .map(user1 -> {
                    user1.setLastName(user.getLastName());
                    user1.setFirstName(user.getFirstName());
                    userRepository.save(user1);
                    return true;
                }).orElse(false);
    }
}
