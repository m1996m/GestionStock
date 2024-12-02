package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.LigneVente;
import com.GestionStock.stock.model.Vente;
import com.GestionStock.stock.repository.LigneVenteRepository;
import com.GestionStock.stock.repository.generic.LigneVenteDAO;
import org.springframework.stereotype.Service;

@Service

public class LigneVenteService extends GenericService<LigneVente, String> {

    public LigneVenteService(LigneVenteDAO ligneVenteDAO, LigneVenteRepository ligneVenteRepository) {
        super(ligneVenteDAO);
    }
}
