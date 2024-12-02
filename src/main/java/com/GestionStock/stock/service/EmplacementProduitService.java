package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.EmplacementProduit;
import com.GestionStock.stock.repository.EmplacementRepository;
import com.GestionStock.stock.repository.generic.EmplacementProduitDAO;
import org.springframework.stereotype.Service;

@Service
public class EmplacementProduitService extends GenericService<EmplacementProduit,String> {

    public EmplacementProduitService(
            EmplacementProduitDAO emplacementProduitDAO, EmplacementRepository emplacementRepository
    ) {
        super(emplacementProduitDAO);
    }
}
