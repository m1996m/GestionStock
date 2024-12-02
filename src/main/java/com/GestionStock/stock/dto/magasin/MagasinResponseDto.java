package com.GestionStock.stock.dto.magasin;

import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.rayon.RayonDto;
import com.GestionStock.stock.dto.stock.StockDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Magasin;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MagasinResponseDto {
    private long idMagasin;
    private String name;
    private String address;
    private String ville;
    private String email;
    private String tel;
    private String slug;
    private LocalDateTime createdAt;
    private List<RayonDto> rayons;
    private List<StockDto> stocks;
    private List<VenteDto> ventes;
    private List<UserDto> users;
    private Long idEntreprise;
    private EntrepriseDto entreprise;
}
