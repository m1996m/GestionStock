package com.GestionStock.stock.dto.stock;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.produitStock.ProduitStockDto;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Stock;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class StockResponseDto {
    private long idStock;
    private String name;
    private String description;
    private String slug;
    private LocalDateTime createdAt;
    private long idMagasin;
    private MagasinDto magasin;
    private List<ProduitStockDto> produitStocks;
}
