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
public class LigneVenteResponseDto {
    private long idLigneVente;
    private int quantite;
    private double puv;
    private String slug;
    private LocalDateTime createdAt;
    private long idVente;
    private long idProduit;
    private VenteDto vente;
    private ProduitDto produit;
}
