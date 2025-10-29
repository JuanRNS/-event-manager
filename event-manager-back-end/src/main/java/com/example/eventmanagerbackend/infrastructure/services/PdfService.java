package com.example.eventmanagerbackend.infrastructure.services;

import com.example.eventmanagerbackend.domain.dtos.EmployeeResponseDTO;
import com.example.eventmanagerbackend.domain.dtos.PdfRequestDashboardDTO;
import com.example.eventmanagerbackend.domain.dtos.PdfRequestDashboardFestaDTO;
import com.example.eventmanagerbackend.domain.entities.EmployeePartiesValues;
import com.example.eventmanagerbackend.domain.entities.Party;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    private final EmployeeService employeeService;

    public PdfService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public byte[] generatePDF(Long id, LocalDate fromDate, LocalDate toDate) {
        PdfRequestDashboardDTO dashboardDTO = dashboardDTO(id, fromDate, toDate);
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (InputStream inputStream = PdfService.class.getResourceAsStream("/relatorioPagamento.png")) {
                if (inputStream == null) {
                    throw new IOException("Não foi possível carregar o arquivo");
                }
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, inputStream.readAllBytes(), "relatorio");
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                contentStream.drawImage(pdImage, 0, 0,
                        page.getMediaBox().getWidth(),
                        page.getMediaBox().getHeight());
                writeText(contentStream, font, 16, 291, 682, initialWeek());
                writeText(contentStream, font, 16, 150, 627, dashboardDTO.name());
                writeText(contentStream, font, 16, 134, 602, formatPhone(dashboardDTO.phone()));
                writeText(contentStream, font, 16, 140, 577, dashboardDTO.pixKey());
                int yTable = 475;
                BigDecimal totalValue = BigDecimal.ZERO;
                String[] aligns = {"left", "center", "right"};
                for (int i = 0; i < dashboardDTO.pdfRequestDashboardFesta().size(); i++) {
                    List<String> values = new ArrayList<>();
                    values.add(dashboardDTO.pdfRequestDashboardFesta().get(i).date());
                    values.add(dashboardDTO.pdfRequestDashboardFesta().get(i).location());
                    values.add(dashboardDTO.pdfRequestDashboardFesta().get(i).valuePerDay().toString());

                    writeRow(contentStream, font, 16, yTable, values, aligns);
                    yTable -= 23;
                    totalValue = totalValue.add(dashboardDTO.pdfRequestDashboardFesta().get(i).valuePerDay());
                }
                writeText(contentStream, font, 16, 500, 228, totalValue.toString());
                contentStream.close();

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                document.save(out);
                return out.toByteArray();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PdfRequestDashboardDTO dashboardDTO(Long id, LocalDate fromDate, LocalDate toDate) {
        EmployeeResponseDTO employee = employeeService.getEmployeeById(id);
        List<Party> parties = employee.parties();
        if (fromDate != null && toDate != null) {
            parties.removeIf(party -> !verifyFromDateAndToDate(party.getDate(), fromDate, toDate));
        }else{
            parties.removeIf(party -> !initialWeek(party.getDate()));
        }
        List<PdfRequestDashboardFestaDTO> partyDTO = parties.stream().map(p ->
                new PdfRequestDashboardFestaDTO(
                        p.getNameClient(),
                        p.getLocation(),
                        p.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        p.getValues()
                                .stream()
                                .filter(value -> value.getEmployeeType() == employee.employeeType())
                                .map(EmployeePartiesValues::getValue)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
        ).toList();

        return new PdfRequestDashboardDTO(
                employee.name(),
                employee.phone(),
                employee.pixKey(),
                partyDTO
        );
    }

    private boolean initialWeek(LocalDateTime todayDate){
        LocalDate todayDateParty = todayDate.toLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastMonday = monday.minusWeeks(1);
        LocalDate lastSunday = lastMonday.plusDays(6);

        return !todayDateParty.isBefore(lastMonday) && !todayDateParty.isAfter(lastSunday);
    }
    private boolean verifyFromDateAndToDate(LocalDateTime partyDate,LocalDate fromDate, LocalDate toDate) {
        LocalDate partyDateParty = partyDate.toLocalDate();
        return !partyDateParty.isBefore(fromDate) && !partyDateParty.isAfter(toDate);
    }

    private void writeText(PDPageContentStream cs, PDFont font, int fontSize, float x, float y, String text) throws IOException {
        cs.beginText();
        cs.setFont(font, fontSize);
        cs.newLineAtOffset(x, y);
        cs.showText(text);
        cs.endText();
    }

    private String initialWeek(){
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate initialWeek = monday.minusWeeks(1);
        LocalDate finalWeek = initialWeek.plusDays(6);

        return initialWeek.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + finalWeek.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String formatPhone(String phone)  {
        return String.format("(%s) %s-%s",
                phone.substring(0, 2),
                phone.substring(2, 7),
                phone.substring(7)
        );
    }

    private void writeRow(PDPageContentStream cs, PDFont font, int fontSize,
                          float y, List<String> values, String[] aligns) throws IOException {
        cs.setFont(font, fontSize);

        float[] colWidths = {150, 210, 120};
        float x = 70;
        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            float textWidth = font.getStringWidth(value) / 1000 * fontSize;
            float cellX = x;

            switch (aligns[i].toLowerCase()) {
                case "center":
                    cellX += (colWidths[i] - textWidth) / 2;
                    break;
                case "right":
                    cellX += colWidths[i] - textWidth;
                    break;
                case "left":
                default:
                    cellX += 0;
            }

            cs.beginText();
            cs.newLineAtOffset(cellX, y);
            cs.showText(value);
            cs.endText();

            x += colWidths[i];
        }
    }

}
