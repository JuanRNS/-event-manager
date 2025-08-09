package com.example.eventmanagerbackend.domain.dtos;

import com.example.eventmanagerbackend.domain.entities.Festa;

import java.util.List;

public record GarcomResponseDTO(
        Long id,
        String nome
) {
}
