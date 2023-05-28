package com.example.url.shortner.microservices.authenticationservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "registered_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String accountType;
    private LocalDateTime accountCreatedAt;

}
