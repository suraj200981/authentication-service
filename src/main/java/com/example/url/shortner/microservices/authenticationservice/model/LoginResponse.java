package com.example.url.shortner.microservices.authenticationservice.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private String token;

    private Long userId;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String accountType;
    private String accountCreatedAt;


    public LoginResponse(String message, String token, User user) {
        this.message = message;
        this.token = token;
        this.userId = user.getId();
        this.emailAddress = user.getEmailAddress();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.accountType = user.getAccountType();
        this.accountCreatedAt = user.getAccountCreatedAt().toString();
    }


}
