package com.GestionStock.stock.dto.ligneVente;

import com.GestionStock.stock.dto.produit.ProduitDto;
import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.model.LigneVente;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Vente;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class LigneVenteDto {
    private long idLigneVente;
    private int quantite;
    private double puv;
    private String slug;
    private LocalDateTime createdAt;
    private long idVente;
    private long idProduit;

    public LigneVente create(LigneVenteDto ligneVenteDto, Vente vente, Produit produit){
        LigneVente ligneVente = new LigneVente();
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPuv(ligneVenteDto.getPuv());
        ligneVente.setProduit(produit);
        ligneVente.setVente(vente);

        return ligneVente;
    }

    public LigneVente update(
            LigneVenteDto ligneVenteDto, Vente vente, Produit produit, LigneVente ligneVente
    ){
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPuv(ligneVenteDto.getPuv());
        ligneVente.setProduit(produit);
        ligneVente.setVente(vente);

        return ligneVente;
    }
}
