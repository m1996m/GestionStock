package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.repository.RayonRepository;
import com.GestionStock.stock.repository.generic.RayonDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RayonService extends GenericService<Rayon, String> {
    private final RayonRepository rayonRepository;

    public RayonService(RayonDAO rayonDAO, RayonRepository rayonRepository){
        super(rayonDAO);
        this.rayonRepository = rayonRepository;
    }

    public List<Rayon> findRayonByMagasin(Magasin magasin) {
        return rayonRepository.findByMagasin(magasin);
    }
}
