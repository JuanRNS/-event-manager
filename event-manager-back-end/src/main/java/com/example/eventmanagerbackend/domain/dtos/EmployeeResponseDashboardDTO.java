package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.EmployeeType;

import java.math.BigDecimal;

public record EmployeeResponseDashboardDTO(
    Long id,
    String name,
    Long totalFestas,
    BigDecimal valueTotal,
    EmployeeType employeeType
) {
}
