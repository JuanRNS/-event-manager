package com.example.eventmanagerbackend.domain.dtos;

public record UserRequestDTO(
    String userName,
    String email,
    String password
) {
}
