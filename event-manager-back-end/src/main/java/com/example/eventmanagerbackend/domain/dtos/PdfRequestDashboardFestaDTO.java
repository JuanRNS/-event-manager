package com.example.eventmanagerbackend.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PdfRequestDashboardFestaDTO(
        String nameClient,
        String location,
        String date,
        BigDecimal valuePerDay
) {
}
