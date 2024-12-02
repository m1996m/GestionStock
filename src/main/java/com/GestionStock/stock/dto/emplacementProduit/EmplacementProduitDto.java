package com.GestionStock.stock.dto.emplacementProduit;

import com.GestionStock.stock.model.Emplacement;
import com.GestionStock.stock.model.EmplacementProduit;
import com.GestionStock.stock.model.Produit;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class EmplacementProduitDto {
    private Long idEmplacementProduit;
    private Long quantite;
    private String slug;
    private LocalDateTime createdAt;
    private Long idEmplacement;
    private Long idProduit;

    public EmplacementProduit create(EmplacementProduitDto emplacementDto, Produit produit, Emplacement emplacement){
        EmplacementProduit emplacementProduit = new EmplacementProduit();
        emplacementProduit.setQuantite(emplacementDto.getQuantite());
        emplacementProduit.setProduit(produit);
        emplacementProduit.setEmplacement(emplacement);

        return emplacementProduit;
    }

    public EmplacementProduit update(EmplacementProduitDto emplacementDto, Produit produit,
                              Emplacement emplacement, EmplacementProduit emplacementProduit
    ){
        emplacementProduit.setQuantite(emplacementDto.getQuantite());
        emplacementProduit.setProduit(produit);
        emplacementProduit.setEmplacement(emplacement);

        return emplacementProduit;
    }
}
