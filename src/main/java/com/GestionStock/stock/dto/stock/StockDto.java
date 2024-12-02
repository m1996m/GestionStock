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
public class StockDto {
    private long idStock;
    private String name;
    private String description;
    private String slug;
    private LocalDateTime createdAt;
    private long idMagasin;

    public Stock create(StockDto stockDto, Magasin magasin){

        Stock stock = new Stock();
        stock.setName(stockDto.getName());
        stock.setDescription(stockDto.getDescription());
        stock.setMagasin(magasin);

        return stock;
    }

    public Stock update(StockDto stockDto, Stock stock){

        stock.setName(stockDto.getName());
        stock.setDescription(stockDto.getDescription());

        return stock;
    }
}
