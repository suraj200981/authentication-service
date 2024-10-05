package com.example.url.shortner.microservices.authenticationservice.controller;

import com.example.url.shortner.microservices.authenticationservice.model.Login;
import com.example.url.shortner.microservices.authenticationservice.model.LoginResponse;
import com.example.url.shortner.microservices.authenticationservice.model.User;
import com.example.url.shortner.microservices.authenticationservice.repository.UsersRepository;
import com.example.url.shortner.microservices.authenticationservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    UsersRepository repo;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody Login login) {
        User search = new User();
        search.setEmailAddress(login.getEmail());
        Optional<User> foundUser = Optional.ofNullable(repo.findByEmailAddressIgnoreCase(search.getEmailAddress()));

        if (foundUser.isPresent()) {
            if (new BCryptPasswordEncoder().matches(login.getPassword(), foundUser.get().getPassword())) {
                // Generate JWT token
                String token = jwtUtil.generateToken(foundUser.get().getEmailAddress());

                // Create a new LoginResponse including the user details and token
                LoginResponse loginResponse = new LoginResponse("Login successful", token, foundUser.get());

                // Return the complete response
                return ResponseEntity.ok(loginResponse);
            } else {
                return new ResponseEntity<>("Password is incorrect", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Email is incorrect", HttpStatus.BAD_REQUEST);
    }

}
