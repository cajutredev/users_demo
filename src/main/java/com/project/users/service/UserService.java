package com.project.users.service;

import com.project.users.dto.UserDto;
import com.project.users.entity.User;
import com.project.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getUsers() {

        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
//        user.setId(UUID.randomUUID().toString().split("-")[0]);
//        users.add(user);

        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        return modelMapper.map(user, UserDto.class);
    }

    public Boolean updateUser(UserDto userDto) {
//        return users.stream()
//                .filter(u -> u.getId().equals(userDto.getId()))
//                .findFirst()
//                .map(user1 -> {
//                    user1.setFirstName(userDto.getFirstName());
//                    user1.setLastName(userDto.getLastName());
//                    return user1;
//                });
        return userRepository.findById(userDto.getId())
                .map(user1 -> {
                        modelMapper.map(userDto, user1);
//                    user1.setLastName(userDto.getLastName());
//                    user1.setFirstName(userDto.getFirstName());
                    userRepository.save(user1);
                    return true;
                }).orElse(false);
    }
}
