package com.example.eventmanagerbackend.domain.dtos;

import java.math.BigDecimal;

public record GarcomResponseDashboardDTO(
    Long id,
    String name,
    Long totalFestas,
    BigDecimal valueTotal
) {
}
