package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Rayon;
import org.springframework.stereotype.Repository;

@Repository
public class ProduitDAO extends GenericDAO<Produit> {
    public ProduitDAO() {
        super(Produit.class);
    }
}
