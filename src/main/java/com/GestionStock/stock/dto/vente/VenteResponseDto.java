package com.GestionStock.stock.dto.vente;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.model.Vente;
import com.GestionStock.stock.repository.generic.ClientDAO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

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
    private ClientDAO client;
    private MagasinDto magasin;
    private UserDto user;
}
