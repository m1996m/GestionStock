package com.GestionStock.stock.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePdfWithTextAndTable() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String logoPath = "src/main/resources/static/image/logo.png";

        try {
            // Initialisation du PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Ligne 1 : Logo et informations du document
            Table headerTable = new Table(2);
            headerTable.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

            // Logo
            ImageData imageData = ImageDataFactory.create(logoPath);
            Image logo = new Image(imageData);
            logo.scaleToFit(100, 50);
            Cell logoCell = new Cell().add(logo).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);

            Paragraph docInfo = new Paragraph("Facture de vente\nNuméro : 12345\nDate : 01/12/2024")
                    .setFontSize(14)
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT);
            Cell infoCell = new Cell().add(docInfo).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);

            headerTable.addCell(logoCell);
            headerTable.addCell(infoCell);
            document.add(headerTable);

            document.add(new Paragraph("\n"));

            Table infoTable = new Table(2);
            infoTable.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

            // Informations de l'entreprise
            Paragraph companyInfo = new Paragraph("Entreprise XYZ\nAdresse : Rue 123, Ville\nContact : 0123456789")
                    .setTextAlignment(TextAlignment.LEFT);
            Cell companyCell = new Cell().add(companyInfo).setBorder(Border.NO_BORDER);

            // Informations du client
            Paragraph clientInfo = new Paragraph("Client ABC\nAdresse : Rue 456, Ville\nContact : 9876543210")
                    .setTextAlignment(TextAlignment.RIGHT);
            Cell clientCell = new Cell().add(clientInfo).setBorder(Border.NO_BORDER);

            infoTable.addCell(companyCell);
            infoTable.addCell(clientCell);
            document.add(infoTable);

            // Espacement avant le tableau
            document.add(new Paragraph("\n"));

            // Tableau des produits
            Table table = new Table(5); // 5 colonnes
            table.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));
            table.setTextAlignment(TextAlignment.CENTER);

            // En-têtes avec couleur de fond
            String[] headers = {"Produit", "Quantité", "Type", "Prix unitaire", "Prix total"};
            for (String header : headers) {
                Cell headerCell = new Cell().add(new Paragraph(header))
                        .setBackgroundColor(new DeviceRgb(211, 211, 211)) // Couleur gris clair (RGB)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(14)
                        .setBold();
                table.addHeaderCell(headerCell);
            }

            // Contenu des lignes
            for (int i = 0; i < 10; i++) {
                table.addCell(new Cell().add(new Paragraph("Produit " + i)));
                table.addCell(new Cell().add(new Paragraph("2")));
                table.addCell(new Cell().add(new Paragraph("Type " + i)));
                table.addCell(new Cell().add(new Paragraph("100")));
                table.addCell(new Cell().add(new Paragraph("200")));
            }

            // Ligne 1 : Logo et informations du document
            Table total = new Table(2);
            headerTable.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

            Paragraph blank = new Paragraph("");

            Paragraph totale = new Paragraph("Total: 200000")
                    .setBackgroundColor(new DeviceRgb(211, 211, 211))
                    .setBold()
                    .setFontSize(15)
                    .setTextAlignment(TextAlignment.RIGHT);

            document.add(table);
            document.add(blank);
            document.add(new Paragraph("\n"));
            document.add(totale);

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }

        return out.toByteArray();
    }
}
