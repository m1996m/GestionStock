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
public class EmplacementProduitResponseDto {
    private Long idEmplacementProduit;
    private Long quantite;
    private String slug;
    private LocalDateTime createdAt;
    private Long idEmplacement;
    private Long idProduit;
}
