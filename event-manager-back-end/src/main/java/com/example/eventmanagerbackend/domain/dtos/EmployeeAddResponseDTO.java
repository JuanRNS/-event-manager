package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusGarcom;

public record GarcomAddResponseDTO(
    Long id,
    String name,
    String phone,
    StatusGarcom statusGarcom
) {
}
