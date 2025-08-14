package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.FestaGarcom;

import java.util.List;

public record GarcomResponseDTO(
    Long id,
    String name,
    String pixKey,
    String phone,
    StatusResponseDTO status
) {
}
