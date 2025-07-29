package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.domain.entities.User;
import com.example.eventmanagerbackend.infrastructure.security.AuthenticationService;
import com.example.eventmanagerbackend.infrastructure.services.UserService;
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
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("login")
    public String login(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }

    @GetMapping("list")
    public List<User> getAll() {
        return userService.findAll();
    }



}
