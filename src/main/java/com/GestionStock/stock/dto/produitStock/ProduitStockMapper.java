package com.GestionStock.stock.dto.produitStock;

import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.ProduitStock;
import com.GestionStock.stock.model.Stock;
import com.GestionStock.stock.service.ProduitService;
import com.GestionStock.stock.service.ProduitStockService;
import com.GestionStock.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class ProduitStockMapper implements GenericMapper<ProduitStock, ProduitStockDto, ProduitStockResponseDto> {
    private final ProduitStockService produitStockService;
    private final StockService stockService;
    private final ProduitService produitService;

    public ProduitStockMapper(ProduitStockService produitStockService, StockService stockService, ProduitService produitService) {
        this.produitStockService = produitStockService;
        this.stockService = stockService;
        this.produitService = produitService;
    }

    @Override
    public ProduitStockDto toDto(ProduitStock produitStock) {
        return ProduitStockDto.builder()
                .idProduitStock(produitStock.getIdProduitStock())
                .idStock(produitStock.getStock().getIdStock())
                .idProduit(produitStock.getProduit().getIdProduit())
                .slug(produitStock.getSlug())
                .createdAt(produitStock.getCreatedAt())
                .quantite(produitStock.getQuantite())
                .build();
    }

    @Override
    public ProduitStockResponseDto toResponseDto(ProduitStock produitStock) {
        return ProduitStockResponseDto.builder()
                .idProduitStock(produitStock.getIdProduitStock())
                .idStock(produitStock.getStock().getIdStock())
                .idProduit(produitStock.getProduit().getIdProduit())
                .slug(produitStock.getSlug())
                .createdAt(produitStock.getCreatedAt())
                .quantite(produitStock.getQuantite())
                .build();
    }

    @Override
    public ProduitStock toEntity(ProduitStockDto produitStockDto) {
        Stock stock = stockService.findById(produitStockDto.getIdStock());
        Produit produit = produitService.findById(produitStockDto.getIdProduit());

        if (produitStockDto.getSlug() != null){
            ProduitStock produitStock = produitStockService.findByValue(produitStockDto.getSlug(), "slug");

            return produitStockDto.update(produitStockDto,stock, produit, produitStock);
        }else{
            return produitStockDto.create(produitStockDto, stock, produit);
        }
    }
}
