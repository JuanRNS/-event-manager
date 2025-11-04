package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusGarcom;

public record EmployeeRequestDTO(
        String name,
        String pixKey,
        String phone,
        Long idEmployeeType,
        StatusGarcom statusGarcom
) {
}
