package com.example.eventmanagerbackend.domain.dtos;


import com.example.eventmanagerbackend.domain.entities.EmployeePartiesValues;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FestaResponseDTO(
    Long id,
    String location,
    String nameClient,
    LocalDateTime date,
    List<EmployeePartiesValues> values,
    MaterialResponseDTO material,
    List<Long> garcomIds,
    Long numberOfPeople,
    StatusFesta status
) {
}
