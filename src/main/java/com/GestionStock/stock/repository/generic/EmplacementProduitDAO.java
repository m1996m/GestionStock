package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.EmplacementProduit;
import com.GestionStock.stock.model.Produit;
import org.springframework.stereotype.Repository;

@Repository
public class EmplacementProduitDAO extends GenericDAO<EmplacementProduit> {
    public EmplacementProduitDAO() {
        super(EmplacementProduit.class);
    }
}
