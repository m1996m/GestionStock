package com.GestionStock.stock.dto.magasin;

import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.entreprise.EntrepriseResponseDto;
import com.GestionStock.stock.dto.rayon.RayonDto;
import com.GestionStock.stock.dto.stock.StockDto;
import com.GestionStock.stock.dto.stock.StockResponseDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.model.Stock;
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
    //private List<Rayon> rayons;
    //private List<Stock> stocks;
    private List<VenteDto> ventes;
    private List<UserDto> users;
    private Long idEntreprise;
    private EntrepriseResponseDto entreprise;
}
