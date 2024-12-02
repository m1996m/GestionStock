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
public class ProduitDto {
    private long idProduit;
    private String name;
    private String image;
    private double prix;
    private String description;
    private String type;
    private String code;
    private String slug;
    private LocalDateTime createdAt;
    private long idRayon;

    public Produit create(ProduitDto produitDto, Rayon rayon){
        Produit produit = new Produit();

        produit.setName(produitDto.getName());
        produit.setPrix(produitDto.getPrix());
        produit.setDescription(produitDto.getDescription());
        produit.setType(produitDto.getType());
        produit.setRayon(rayon);

        return produit;
    }

    public Produit update(ProduitDto produitDto, Produit produit){
        produit.setName(produitDto.getName());
        produit.setPrix(produitDto.getPrix());
        produit.setDescription(produitDto.getDescription());
        produit.setType(produitDto.getType());
        produit.setImage(produitDto.getImage());

        return produit;
    }
}
