package com.example.url.shortner.microservices.authenticationservice.controller;


import com.example.url.shortner.microservices.authenticationservice.model.User;
import com.example.url.shortner.microservices.authenticationservice.repository.UsersRepository;
import com.example.url.shortner.microservices.authenticationservice.utils.validators.FirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://localhost:8080/")
public class RegistrationController {

    @Autowired
    UsersRepository repo;

    @Autowired
    FirstNameValidator firstNameValidator;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User regVals) {

        User newUser = new User();

        if (regVals != null) {
            if (firstNameValidator.isValid(regVals.getFirstName().toLowerCase())) {
                newUser.setFirstName(regVals.getFirstName());

            } else {
                return new ResponseEntity<>("First name is incorrect", HttpStatus.BAD_REQUEST);
            }
            newUser.setFirstName(regVals.getFirstName());
            newUser.setLastName(regVals.getLastName());
            newUser.setEmailAddress(regVals.getEmailAddress());
            newUser.setAccountType(regVals.getAccountType());
            newUser.setAccountCreatedAt(LocalDateTime.now());
            String encodedPassword = new BCryptPasswordEncoder().encode(regVals.getPassword());
            newUser.setPassword(encodedPassword);
            repo.save(newUser);
            return ResponseEntity.ok(String.format("%s your account has been created", newUser.getFirstName()));
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }
}
