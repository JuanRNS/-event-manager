package com.example.eventmanagerbackend.infrastructure.repositories;

import com.example.eventmanagerbackend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String name);
}
