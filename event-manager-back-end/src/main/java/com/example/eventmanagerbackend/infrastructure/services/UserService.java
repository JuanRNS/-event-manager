package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.UserRequestDTO;
import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.infrastructure.exceptions.EmailExistsException;
import com.example.eventmanagerbackend.infrastructure.exceptions.UserNameExistsException;
import com.example.eventmanagerbackend.infrastructure.exceptions.UserNameStringFirstException;
import com.example.eventmanagerbackend.infrastructure.exceptions.UserNotFoundException;
import com.example.eventmanagerbackend.infrastructure.repositories.UserRepository;
import org.springframework.security.core.Authentication;
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
        if(!validateUserName(user.userName())) {
            throw new UserNameStringFirstException();
        }
        if (validateEmail(user.email())) {
            throw new EmailExistsException();
        }
        if (validateName(user.userName())) {
            throw new UserNameExistsException();
        }
        User createdUser = parseUser(user);
        return userRepository.save(createdUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUser(Authentication authentication) {
        return userRepository.findByUserName(authentication.getName()).orElseThrow(UserNotFoundException::new);
    }

    private User parseUser(UserRequestDTO user) {
        User createdUser = new User();
        createdUser.setEmail(user.email());
        createdUser.setUserName(user.userName());
        createdUser.setPassword(passwordEncoder.encode(user.password()));
        return createdUser;
    }

    private boolean validateUserName(String userName) {
        return userName != null && userName.matches("^[A-Za-z].*");
    }

    private boolean validateEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean validateName(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
