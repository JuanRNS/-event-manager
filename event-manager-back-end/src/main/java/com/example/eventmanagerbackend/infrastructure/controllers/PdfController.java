package com.example.eventmanagerbackend.infrastructure.controllers;

import com.example.eventmanagerbackend.infrastructure.services.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/generate/garcom/{id}")
    public ResponseEntity<byte[]> generatePDF(
            @PathVariable Long id,
            @RequestParam(required = false) Optional<LocalDate> fromDate,
            @RequestParam(required = false) Optional<LocalDate> toDate
    ) throws Exception {
        byte[] pdf = pdfService.generatePDF(
                id,
                fromDate.orElse(null),
                toDate.orElse(null)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=garcom_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
