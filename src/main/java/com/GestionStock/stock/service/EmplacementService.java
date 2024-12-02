package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Emplacement;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.repository.EmplacementRepository;
import com.GestionStock.stock.repository.generic.EmplacementDAO;
import org.springframework.stereotype.Service;

@Service
public class EmplacementService extends GenericService<Emplacement,String> {

    public EmplacementService(
            EmplacementDAO emplacementDAO, EmplacementRepository emplacementRepository
    ) {
        super(emplacementDAO);
    }
}
