package com.example.eventmanagerbackend.domain.dtos;

public record UserRequestDTO(
    String username,
    String email,
    String password
) {
}
