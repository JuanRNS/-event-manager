package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusGarcom;

public record GarcomRequestDTO(
        String name,
        String pixKey,
        String phone,
        StatusGarcom statusGarcom
) {
}
