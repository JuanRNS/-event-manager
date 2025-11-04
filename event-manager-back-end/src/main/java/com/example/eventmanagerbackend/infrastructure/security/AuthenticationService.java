package com.example.eventmanagerbackend.infrastructure.security;

import com.example.eventmanagerbackend.domain.dtos.UserLoginRequestDTO;
import com.example.eventmanagerbackend.infrastructure.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(UserLoginRequestDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.username(), user.password())
        );
        return jwtService.generateToken(authentication);
    }

}
