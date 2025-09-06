package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.Material;
import com.example.eventmanagerbackend.domain.enums.StatusFesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FestaGarcomViewDTO(
        Long id,
        String location,
        String nameClient,
        LocalDateTime date,
        BigDecimal valuePerDay,
        Material material,
        Long numberOfPeople,
        List<GarcomResponseDTO> garcoms,
        StatusFesta status
) {
}
