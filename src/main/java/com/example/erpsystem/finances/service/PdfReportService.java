package com.example.erpsystem.finances.service;

import com.example.erpsystem.finances.model.Transaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfReportService {

    public byte[] generateTransactionReport(List<Transaction> transactions) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Wczytaj font TTF (np. DejaVu Sans)
            PDType0Font font = PDType0Font.load(document, new File("src/main/resources/DejaVuSans.ttf"));

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(font, 18);
                contentStream.beginText();
                contentStream.setLeading(20);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Raport finansowy");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText("Lista transakcji:");
                contentStream.newLine();
                contentStream.newLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (Transaction transaction : transactions) {
                    String line = String.format("Data: %s, Opis: %s, Typ: %s, Kwota: %.2f",
                            transaction.getDate().format(formatter),
                            transaction.getDescription(),
                            transaction.getType(),
                            transaction.getAmount());
                    contentStream.showText(line);
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            byte[] pdfBytes;
            try (var outputStream = new java.io.ByteArrayOutputStream()) {
                document.save(outputStream);
                pdfBytes = outputStream.toByteArray();
            }

            return pdfBytes;
        }
    }
}
