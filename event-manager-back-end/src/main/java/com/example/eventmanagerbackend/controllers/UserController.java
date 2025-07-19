package com.example.eventmanagerbackend.controllers;

import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.domain.infrastructure.security.AuthenticationService;
import com.example.eventmanagerbackend.domain.infrastructure.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public String login(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }

    @GetMapping("all")
    public List<User> getAll() {
        return userService.findAll();
    }



}
