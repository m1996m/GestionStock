package com.GestionStock.stock.dto.entreprise;

import com.GestionStock.stock.dto.client.ClientDto;
import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.user.UserDto;
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
public class EntrepriseDto {
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

    public EntrepriseDto toDto(Entreprise entreprise){
        return EntrepriseDto.builder()
                .name(entreprise.getName())
                .address(entreprise.getAddress())
                .tel(entreprise.getTel())
                .idEntreprise(entreprise.getIdEntreprise())
                .email(entreprise.getEmail())
                .slug(entreprise.getSlug())
                .logo(entreprise.getLogo())
                .ville(entreprise.getVille())
                .createdAt(entreprise.getCreatedAt())
                .build();
    }

    public Entreprise create(EntrepriseDto entrepriseDto){
        Entreprise entreprise = new Entreprise();

        entreprise.setName(entrepriseDto.getName());
        entreprise.setAddress(entrepriseDto.getAddress());
        entreprise.setTel(entrepriseDto.getTel());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setVille(entrepriseDto.getVille());

        return entreprise;
    }

    public Entreprise uploadLogo(String logo){
        Entreprise entreprise = new Entreprise();

        entreprise.setLogo(getLogo());

        return  entreprise;
    }


}

