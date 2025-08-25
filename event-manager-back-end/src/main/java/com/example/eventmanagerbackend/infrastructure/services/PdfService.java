package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.GarcomResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.PdfRequestDashboardDTO;
import com.example.eventmanagerbackend.domain.dtos.PdfRequestDashboardFestaDTO;
import com.example.eventmanagerbackend.domain.entities.Festa;
import com.example.eventmanagerbackend.domain.entities.Garcom;
import lombok.extern.java.Log;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {

    private final GarcomService garcomService;
    private final FestaGarcomService festaGarcomService;

    public PdfService(GarcomService garcomService, FestaGarcomService festaGarcomService) {
        this.garcomService = garcomService;
        this.festaGarcomService = festaGarcomService;
    }

    public byte[] generatePDF(Long id) {
        PdfRequestDashboardDTO dashboardDTO = dashboardDTO(id);
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            float fontSizeH1 = 24f;
            float yPosition = 750;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            String titulo = "Balloons Recepções";
            float titleWidth = font.getStringWidth(titulo) / 1000 * fontSizeH1;
            float pageWidth = page.getMediaBox().getWidth();
            float xPosition = (pageWidth - titleWidth) / 2;

            contentStream.beginText();
            contentStream.setFont(font, fontSizeH1);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(titulo);
            contentStream.endText();

            yPosition -= 50;

            contentStream.beginText();
            contentStream.setFont(font, 16);
            contentStream.newLineAtOffset(50, yPosition);

            contentStream.showText("Garçom: " + dashboardDTO.name());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Telefone: " + dashboardDTO.phone());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Chave Pix: " + dashboardDTO.pixKey());
            contentStream.newLineAtOffset(0, -40);

            contentStream.setFont(font, 16);
            BigDecimal total = BigDecimal.ZERO;
            for (PdfRequestDashboardFestaDTO festa : dashboardDTO.pdfRequestDashboardFesta()) {
                String linha = String.format("%s | %s | %s | R$ %.2f",
                        festa.date(),
                        festa.location(),
                        festa.nameClient(),
                        festa.valuePerDay()
                );
                contentStream.showText(linha);
                contentStream.newLineAtOffset(0, -20);
                total = total.add(festa.valuePerDay());
            }

            contentStream.newLineAtOffset(0, -40);
            contentStream.setFont(font, 16);
            contentStream.showText("Total a receber: R$ " + total.setScale(2, RoundingMode.HALF_UP));

            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return out.toByteArray();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PdfRequestDashboardDTO dashboardDTO(Long id){
        GarcomResponseDTO garcom = garcomService.getGarcomById(id);
        List<Festa> festas = festaGarcomService.getFestaGarcomById(id);
        List<PdfRequestDashboardFestaDTO> festasDTO = festas.stream().map(f ->
                new PdfRequestDashboardFestaDTO(
                        f.getNameClient(),
                        f.getLocation(),
                        f.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        f.getValuePerDay()
                )
        ).toList();

        return new PdfRequestDashboardDTO(
                garcom.name(),
                garcom.phone(),
                garcom.pixKey(),
                festasDTO
        );
    }
}
