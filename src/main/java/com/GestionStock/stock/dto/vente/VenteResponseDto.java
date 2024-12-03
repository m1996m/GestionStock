package com.GestionStock.stock.dto.vente;

import com.GestionStock.stock.dto.client.ClientDto;
import com.GestionStock.stock.dto.client.ClientResponseDto;
import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.magasin.MagasinResponseDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.dto.user.UserResponseDto;
import com.GestionStock.stock.model.*;
import com.GestionStock.stock.repository.generic.ClientDAO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class VenteResponseDto {
    private long idVente;
    private Date dateVente;
    private String slug;
    private LocalDateTime createdAt;
    private long idClient;
    private long idMagasin;
    private long idUser;
    private ClientResponseDto client;
    private MagasinResponseDto magasin;
    private UserResponseDto user;
    //private List<LigneVente> ligneVentes;
}
