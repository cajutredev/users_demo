package com.project.users.dto;


import com.project.users.entity.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDto addressDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
