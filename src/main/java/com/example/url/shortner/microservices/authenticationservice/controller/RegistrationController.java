package com.example.url.shortner.microservices.authenticationservice.controller;


import com.example.url.shortner.microservices.authenticationservice.model.Register;
import com.example.url.shortner.microservices.authenticationservice.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RegistrationController {

    @Autowired
    RegisterRepository repo;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Register regVals) {

        Register newUser = new Register();
        if (regVals != null) {
            newUser.setFirstName(regVals.getFirstName());
            newUser.setLastName(regVals.getLastName());
            newUser.setEmailAddress(regVals.getEmailAddress());
            newUser.setAccountCreatedAt(LocalDateTime.now());
            String encodedPassword = new BCryptPasswordEncoder().encode(regVals.getPassword());
            newUser.setPassword(encodedPassword);
            repo.save(newUser);
            return ResponseEntity.ok(String.format("%s your account has been created", newUser.getFirstName()));
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }
}
