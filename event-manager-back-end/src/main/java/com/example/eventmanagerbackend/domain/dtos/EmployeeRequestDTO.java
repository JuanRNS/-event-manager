package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;
import com.example.eventmanagerbackend.domain.enums.StatusGarcom;

public record EmployeeRequestDTO(
        String name,
        String pixKey,
        String phone,
        EmployeeType employeeType,
        StatusGarcom statusGarcom
) {
}
