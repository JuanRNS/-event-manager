package com.example.eventmanagerbackend.domain.dtos;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FestaRequestDTO(
        String nameClient,
        String location,
        LocalDateTime date,
        Long idMaterial,
        Long numberOfPeople
) {
}
