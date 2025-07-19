package com.example.eventmanagerbackend.domain.infrastructure.services;

import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.domain.infrastructure.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        User createdUser = userRepository.save(user);
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(createdUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
