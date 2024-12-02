package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Emplacement;
import org.springframework.stereotype.Repository;

@Repository
public class EmplacementDAO extends GenericDAO<Emplacement> {
    public EmplacementDAO() {
        super(Emplacement.class);
    }
}
