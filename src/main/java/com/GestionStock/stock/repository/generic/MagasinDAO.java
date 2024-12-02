package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Magasin;
import org.springframework.stereotype.Repository;

@Repository
public class MagasinDAO extends GenericDAO<Magasin> {
    public MagasinDAO() {
        super(Magasin.class);
    }
}
