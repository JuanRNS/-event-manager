package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.Status;


public record GarcomResponseDTO(
    Long id,
    String name,
    String pixKey,
    String phone,
    Status status
) {
}
