package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.UserRequestDTO;
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

    public User createUser(UserRequestDTO user) {
        String email = user.email();
        boolean emailExists = userRepository.findByEmail(email).isPresent();
        if (emailExists) {
            throw new EmailExistsException();
        }
        User createdUser = parseUser(user);
        return userRepository.save(createdUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    private User parseUser(UserRequestDTO user) {
        User createdUser = new User();
        createdUser.setEmail(user.email());
        createdUser.setUserName(user.username());
        createdUser.setPassword(passwordEncoder.encode(user.password()));
        return createdUser;
    }
}
