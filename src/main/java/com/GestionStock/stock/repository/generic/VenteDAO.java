package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Vente;
import org.springframework.stereotype.Repository;

@Repository
public class VenteDAO extends GenericDAO<Vente> {
    public VenteDAO() {
        super(Vente.class);
    }
}
