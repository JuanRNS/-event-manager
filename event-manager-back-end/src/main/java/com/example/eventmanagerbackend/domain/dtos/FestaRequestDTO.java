package com.example.eventmanagerbackend.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FestaRequestDTO(
        String nomeCliente,
        String local,
        LocalDateTime data,
        BigDecimal valorDiariaGarcom,
        Long idMaterial) {
}
