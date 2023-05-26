package com.example.url.shortner.microservices.authenticationservice.model;

import lombok.Data;

@Data
public class Login {

    private String email;

    private String password;
}
