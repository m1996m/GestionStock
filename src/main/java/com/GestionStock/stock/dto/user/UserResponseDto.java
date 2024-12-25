package com.GestionStock.stock.dto.user;

import com.GestionStock.stock.listEnumeration.Genre;
import com.GestionStock.stock.listEnumeration.Role;
import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.magasin.MagasinResponseDto;
import com.GestionStock.stock.model.Vente;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private long idUser;
    private String email;
    private String firstName;
    private String tel;
    private String address;
    private String lastName;
    private String password;
    private String slug;
    private Role role = Role.USER;
    private String avatar;
    private Genre gender = Genre.AUTRE;
    private LocalDateTime createdAt;
    private long idEntreprise;
    private long idMagasin;
    private MagasinResponseDto magasin;
    private EntrepriseDto entreprise;
    private List<Vente> ventes;
}
