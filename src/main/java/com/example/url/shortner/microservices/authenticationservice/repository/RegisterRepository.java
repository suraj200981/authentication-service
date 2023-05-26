package com.example.url.shortner.microservices.authenticationservice.repository;


import com.example.url.shortner.microservices.authenticationservice.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByemailAddress(String emailAddress);
}
