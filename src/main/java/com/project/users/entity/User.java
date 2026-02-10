package com.project.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "_user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String email;
    @Column
    private String phone;
    private UserRole role = UserRole.CUSTOMER;
}
