package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.repository.ProduitRepository;
import com.GestionStock.stock.repository.generic.ProduitDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService extends GenericService<Produit, String> {

    public ProduitService(ProduitDAO produitDAO, ProduitRepository produitRepository) {
        super(produitDAO);
    }
}
