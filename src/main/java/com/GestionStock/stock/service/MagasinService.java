package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.repository.MagasinRepository;
import com.GestionStock.stock.repository.generic.MagasinDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagasinService extends GenericService<Magasin, String> {

    public MagasinService(MagasinDAO magasinDAO, MagasinRepository magasinRepository) {
        super(magasinDAO);
    }
}
