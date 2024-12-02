package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.ProduitStock;
import org.springframework.stereotype.Repository;

@Repository
public class ProduitStockDAO extends GenericDAO<ProduitStock> {

    public ProduitStockDAO() {
        super(ProduitStock.class);
    }
}
