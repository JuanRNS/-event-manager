package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusGarcom;


public record GarcomResponseDTO(
    Long id,
    String name,
    String pixKey,
    String phone,
    StatusGarcom statusGarcom
) {
}
