package com.example.url.shortner.microservices.authenticationservice.controller;

import com.example.url.shortner.microservices.authenticationservice.model.Login;
import com.example.url.shortner.microservices.authenticationservice.model.Register;
import com.example.url.shortner.microservices.authenticationservice.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    RegisterRepository repo;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody Login login) {

        Register search = new Register();
        search.setEmailAddress(login.getEmail());
        Optional<Register> foundUser = Optional.ofNullable(repo.findByemailAddress(search.getEmailAddress()));

        if (foundUser.isPresent()) {
            if (login.getEmail().equals(foundUser.get().getEmailAddress())) {
                if (new BCryptPasswordEncoder().matches(login.getPassword(), foundUser.get().getPassword())) {
                    return new ResponseEntity<>("Login successfull, welcome back " + foundUser.get().getFirstName(), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Password is incorrect", HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity<>("Email is incorrect", HttpStatus.BAD_REQUEST);
    }
}
