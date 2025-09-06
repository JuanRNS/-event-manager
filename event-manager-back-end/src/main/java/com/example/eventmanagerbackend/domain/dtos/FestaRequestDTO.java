package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusFesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FestaRequestDTO(
        String nameClient,
        String location,
        LocalDateTime date,
        BigDecimal valuePerDay,
        Long idMaterial,
        Long numberOfPeople
) {
}
