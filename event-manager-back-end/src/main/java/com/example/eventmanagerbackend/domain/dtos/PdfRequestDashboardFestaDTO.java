package com.example.eventmanagerbackend.domain.dtos;

import java.math.BigDecimal;

public record PdfRequestDashboardFestaDTO(
        String nameClient,
        String location,
        String date,
        BigDecimal values
) {
}
