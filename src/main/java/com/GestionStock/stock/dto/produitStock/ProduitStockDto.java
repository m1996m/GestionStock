package com.GestionStock.stock.dto.produitStock;

import com.GestionStock.stock.dto.produit.ProduitDto;
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
public class ProduitStockDto {
    private long idProduitStock;
    private int quantite;
    private String slug;
    private LocalDateTime createdAt;
    private long idStock;
    private long idProduit;

    public ProduitStock create(ProduitStockDto produitStockDto, Stock stock, Produit produit){
        ProduitStock produitStock = new ProduitStock();
        produitStock.setQuantite(produitStockDto.getQuantite());
        produitStock.setStock(stock);
        produitStock.setProduit(produit);

        return produitStock;
    }

    public ProduitStock update(ProduitStockDto produitStockDto, Stock stock, Produit produit, ProduitStock produitStock){
        produitStock.setQuantite(produitStockDto.getQuantite());
        produitStock.setStock(stock);
        produitStock.setProduit(produit);

        return produitStock;
    }

}
