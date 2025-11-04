package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusGarcom;

public record EmployeeAddResponseDTO(
    Long id,
    String name,
    String phone,
    StatusGarcom statusGarcom
) {
}
