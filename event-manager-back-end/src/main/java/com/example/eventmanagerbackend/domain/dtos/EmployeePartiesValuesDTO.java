package com.example.eventmanagerbackend.domain.dtos;

import java.math.BigDecimal;

public record EmployeePartiesValuesDTO(
        Long idEmployeeType,
        BigDecimal value
) {
}
