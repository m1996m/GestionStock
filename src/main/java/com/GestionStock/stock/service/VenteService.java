package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Vente;
import com.GestionStock.stock.repository.VenteRepository;
import com.GestionStock.stock.repository.generic.VenteDAO;
import org.springframework.stereotype.Service;

@Service
public class VenteService extends GenericService<Vente, String> {

    public VenteService(VenteDAO venteDAO, VenteRepository venteRepository) {
        super(venteDAO);
    }
}
