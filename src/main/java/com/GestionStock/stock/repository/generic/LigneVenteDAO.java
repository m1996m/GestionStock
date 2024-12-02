package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.LigneVente;
import com.GestionStock.stock.model.Vente;
import org.springframework.stereotype.Repository;

@Repository
public class LigneVenteDAO extends GenericDAO<LigneVente> {
    public LigneVenteDAO() {
        super(LigneVente.class);
    }
}
