package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.FestaGarcomId;

import java.math.BigDecimal;

public record FestaGarcomResponseDTO(
    FestaGarcomId id,
    BigDecimal valuePerDay
) {
}
