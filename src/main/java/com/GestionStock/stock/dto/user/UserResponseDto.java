package com.GestionStock.stock.dto.user;

import com.GestionStock.stock.ListEnumeration.Genre;
import com.GestionStock.stock.ListEnumeration.Role;
import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Users;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private MagasinDto magasin;
    private EntrepriseDto entreprise;
    private List<VenteDto> ventes;
    private EntrepriseDto entrepriseDto;
    private MagasinMapper magasinMapper;
}
