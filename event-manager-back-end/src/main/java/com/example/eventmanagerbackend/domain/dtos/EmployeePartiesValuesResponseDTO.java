package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;

import java.math.BigDecimal;

public record EmployeePartiesValuesResponseDTO(
        Long id,
        EmployeeType employeeType,
        BigDecimal value
) {
}
