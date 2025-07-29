package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmailExistsException;
import com.example.eventmanagerbackend.infrastructure.repositories.UserRepository;
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
        String email = user.getEmail();
        boolean emailExists = userRepository.findByEmail(email).isPresent();
        if (emailExists) {
            throw new EmailExistsException();
        }
        User createdUser = userRepository.save(user);
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(createdUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
