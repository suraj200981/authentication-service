package com.example.url.shortner.microservices.authenticationservice.repository;


import com.example.url.shortner.microservices.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByemailAddress(String emailAddress);
}
