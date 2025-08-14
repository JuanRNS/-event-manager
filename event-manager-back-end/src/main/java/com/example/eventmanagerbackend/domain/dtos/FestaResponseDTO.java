package com.example.eventmanagerbackend.domain.dtos;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FestaResponseDTO(
    Long id,
    String local,
    String nomeCliente,
    LocalDateTime data,
    BigDecimal valorDiariaGarcom,
    MaterialResponseDTO material,
    List<Long> garcomIds
) {
}
