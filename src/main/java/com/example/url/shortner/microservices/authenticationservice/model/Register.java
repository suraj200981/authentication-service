package com.example.url.shortner.microservices.authenticationservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "registered_users")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private LocalDateTime accountCreatedAt;

}
