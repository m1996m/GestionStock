package com.GestionStock.stock.pdf;

import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.LigneVente;
import com.GestionStock.stock.service.LigneVenteService;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final LigneVenteService ligneVenteService;

    public byte[] generatePdf(Long id) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String logoPath = "src/main/resources/static/image/logo.png";

        List<LigneVente> ligneVente = ligneVenteService.findDynamicByOneTable("vente","idVente", id);
        System.out.println(ligneVente.toString());

        String client = formatClientInfo(ligneVente.get(0).getVente().getClient());
        String entreprise = formatEntrepriseInfo(ligneVente.get(0).getVente().getClient().getEntreprise());

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Table headerTable = new Table(2);
            headerTable.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

            ImageData imageData = ImageDataFactory.create(logoPath);
            Image logo = new Image(imageData);
            logo.scaleToFit(100, 50);
            Cell logoCell = new Cell().add(logo).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);

            Date dateVente = ligneVente.get(0).getVente().getDateVente();
            LocalDate localDate = dateVente.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = localDate.format(formatter);

            Paragraph docInfo = new Paragraph("Facture de vente\nNuméro : "+ligneVente.get(0).getVente().getCode()+"\nDate : "+formattedDate)
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
            Paragraph companyInfo = new Paragraph(entreprise)
                    .setTextAlignment(TextAlignment.LEFT);
            Cell companyCell = new Cell().add(companyInfo).setBorder(Border.NO_BORDER);

            // Informations du client
            Paragraph clientInfo = new Paragraph(client)
                    .setTextAlignment(TextAlignment.RIGHT);
            Cell clientCell = new Cell().add(clientInfo).setBorder(Border.NO_BORDER);

            infoTable.addCell(companyCell);
            infoTable.addCell(clientCell);
            document.add(infoTable);

            document.add(new Paragraph("\n"));

            // Tableau des produits
            Table table = new Table(5);
            table.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));
            table.setTextAlignment(TextAlignment.CENTER);

            // En-têtes
            String[] headers = {"Produit", "Quantité", "Type", "Prix unitaire", "Prix total"};
            for (String header : headers) {
                Cell headerCell = new Cell().add(new Paragraph(header))
                        .setBackgroundColor(new DeviceRgb(211, 211, 211))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(14)
                        .setBold();
                table.addHeaderCell(headerCell);
            }

            double sommeTotal = 0;

            // Contenu des lignes
            for (LigneVente ligne : ligneVente) {
                table.addCell(new Cell().add(new Paragraph(ligne.getProduit().getName())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(ligne.getQuantite()))));
                table.addCell(new Cell().add(new Paragraph(ligne.getProduit().getType())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(ligne.getPuv()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(ligne.getPuv() * ligne.getQuantite()))));

                sommeTotal += ligne.getPuv() * ligne.getQuantite();
            }

            Table total = new Table(2);
            total.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

            Paragraph blank = new Paragraph("");

            Paragraph totale = new Paragraph("Total: "+sommeTotal)
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

    public String formatEntrepriseInfo(Entreprise entreprise) {
        if (entreprise == null) {
            return "Informations de l'entreprise non disponibles.";
        }

        return new StringBuilder()
                .append("Nom de l'entreprise: ").append(entreprise.getName()).append("\n")
                .append("Adresse: ").append(entreprise.getAddress()).append("\n")
                .append("Contact: ").append(entreprise.getTel()).append("\n")
                .append("Email: ").append(entreprise.getEmail()).append("\n")
                .toString();
    }

    public String formatClientInfo(Client client) {
        if (client == null) {
            return "Informations du client non disponibles.";
        }

        return new StringBuilder()
                .append("Nom du client: ").append(client.getFirstName()).append(" ")
                .append(client.getLastName()).append("\n")
                .append("Adresse: ").append(client.getAddress()).append("\n")
                .append("Contact: ").append(client.getTel()).append("\n")
                .append("Email: ").append(client.getEmail()).append("\n")
                .toString();
    }
}
