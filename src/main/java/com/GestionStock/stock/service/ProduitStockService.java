package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.ProduitStock;
import com.GestionStock.stock.repository.ProduitStockRepository;
import com.GestionStock.stock.repository.generic.ProduitStockDAO;
import org.springframework.stereotype.Service;

@Service
public class ProduitStockService extends GenericService<ProduitStock, String> {
    private final ProduitStockRepository produitStockRepository;

    public ProduitStockService(
            ProduitStockDAO produitStockDAO, ProduitStockRepository produitStockRepository
    ) {
        super(produitStockDAO);
        this.produitStockRepository = produitStockRepository;
    }
}
