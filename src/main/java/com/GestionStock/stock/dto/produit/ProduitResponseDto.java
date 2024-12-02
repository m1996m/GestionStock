package com.GestionStock.stock.dto.produit;

import com.GestionStock.stock.dto.ligneVente.LigneVenteDto;
import com.GestionStock.stock.dto.produitStock.ProduitStockDto;
import com.GestionStock.stock.dto.rayon.RayonDto;
import com.GestionStock.stock.model.EmplacementProduit;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Rayon;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ProduitResponseDto {
    private long idProduit;
    private String name;
    private String image;
    private double prix;
    private String description;
    private String type;
    private String slug;
    private LocalDateTime createdAt;
    private long idRayon;
    private RayonDto rayon;
    private List<ProduitStockDto> produitStocks;
    private List<LigneVenteDto> ligneVentes;
    private List<EmplacementProduit> emplacementProduits;
}
