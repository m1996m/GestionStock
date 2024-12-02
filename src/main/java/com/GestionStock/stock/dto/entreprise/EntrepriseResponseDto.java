package com.GestionStock.stock.dto.entreprise;

import com.GestionStock.stock.dto.client.ClientDto;
import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Entreprise;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntrepriseResponseDto {
    private Long idEntreprise;
    private String name;
    private String address;
    private String tel;
    private String ville;
    private String email;
    private String logo;
    private String slug;
    private LocalDateTime createdAt;
    private List<UserDto> users;
    private List<MagasinDto> magasins;
    private List<ClientDto> clients;
}

