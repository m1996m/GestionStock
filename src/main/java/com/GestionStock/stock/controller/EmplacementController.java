package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.emplacement.EmplacementDto;
import com.GestionStock.stock.dto.emplacement.EmplacementResponseDto;
import com.GestionStock.stock.dto.entreprise.EntrepriseResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Emplacement;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.service.ProduitService;
import com.GestionStock.stock.service.RayonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emplacement")
public class EmplacementController extends GenericController<Emplacement, String, EmplacementDto, EmplacementResponseDto> {

    public EmplacementController(
            GenericService<Emplacement, String> genericService, GenericMapper<Emplacement,
            EmplacementDto, EmplacementResponseDto> genericMapper
    ) {
        super(genericService, genericMapper);
    }
}
