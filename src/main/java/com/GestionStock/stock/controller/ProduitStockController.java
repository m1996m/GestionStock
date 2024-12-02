package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.produitStock.ProduitStockDto;
import com.GestionStock.stock.dto.produitStock.ProduitStockResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.ProduitStock;
import com.GestionStock.stock.service.ProduitService;
import com.GestionStock.stock.service.ProduitStockService;
import com.GestionStock.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produit/stock")
public class ProduitStockController extends GenericController<ProduitStock, String, ProduitStockDto, ProduitStockResponseDto> {

    public ProduitStockController(
            GenericService<ProduitStock, String> genericService, GenericMapper<ProduitStock,
            ProduitStockDto, ProduitStockResponseDto> genericMapper
    ) {
        super(genericService, genericMapper);
    }
}
