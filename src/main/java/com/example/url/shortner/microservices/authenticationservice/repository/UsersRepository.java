package com.example.url.shortner.microservices.authenticationservice.repository;


import com.example.url.shortner.microservices.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.emailAddress) = LOWER(:email)")
    User findByEmailAddressIgnoreCase(@Param("email") String email);}
