package com.example.eventmanagerbackend.domain.dtos;

import java.util.List;

public record PdfRequestDashboardDTO(
        String name,
        String phone,
        String pixKey,
        List<PdfRequestDashboardFestaDTO> pdfRequestDashboardParty
) {
}
