package com.GestionStock.stock.dto.produitStock;

import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.ProduitStock;
import com.GestionStock.stock.model.Stock;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ProduitStockResponseDto {
    private long idProduitStock;
    private int quantite;
    private String slug;
    private LocalDateTime createdAt;
    private long idStock;
    private long idProduit;
}
