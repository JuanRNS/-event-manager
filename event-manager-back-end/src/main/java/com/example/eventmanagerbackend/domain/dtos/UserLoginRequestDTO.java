package com.example.eventmanagerbackend.domain.dtos;

public record UserLoginRequestDTO(
        String username,
        String password
) {
}
