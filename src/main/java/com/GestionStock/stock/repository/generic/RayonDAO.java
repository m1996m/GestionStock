package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Rayon;
import org.springframework.stereotype.Repository;

@Repository
public class RayonDAO extends GenericDAO<Rayon> {
    public RayonDAO(){
        super(Rayon.class);
    }
}
