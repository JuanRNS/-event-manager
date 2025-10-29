package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.enums.StatusFesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FestasByGarcomIdDTO(
        String location,
        String nameClient,
        LocalDateTime date,
        BigDecimal valuePerDay,
        StatusFesta status,
        Long numberOfPeople
) {
}
