package com.GestionStock.stock.dto.client;

import com.GestionStock.stock.ListEnumeration.Genre;
import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ClientResponseDto {
    private long idClient;
    private String email;
    private String firstName;
    private String tel;
    private String lastName;
    private String address;
    private String slug;
    private Genre genre = Genre.AUTRE;
    private LocalDateTime createdAt;
    private long idEntreprise;
    private EntrepriseDto entreprise;
    private List<VenteDto> ventes;
}