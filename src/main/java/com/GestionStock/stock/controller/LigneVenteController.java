package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.ligneVente.LigneVenteDto;
import com.GestionStock.stock.dto.ligneVente.LigneVenteMapper;
import com.GestionStock.stock.dto.ligneVente.LigneVenteResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.LigneVente;
import com.GestionStock.stock.service.LigneVenteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ligne/vente")
public class LigneVenteController extends GenericController<LigneVente, String, LigneVenteDto, LigneVenteResponseDto> {

    private final LigneVenteService ligneVenteService;
    private final LigneVenteMapper ligneVenteMapper;

    public LigneVenteController(
            GenericService<LigneVente, String> genericService, GenericMapper<LigneVente,
                    LigneVenteDto, LigneVenteResponseDto> genericMapper,
            LigneVenteService ligneVenteService, LigneVenteMapper ligneVenteMapper) {
        super(genericService, genericMapper);
        this.ligneVenteService = ligneVenteService;
        this.ligneVenteMapper = ligneVenteMapper;
    }

    @GetMapping("/vente/{id}")
    public List<LigneVenteDto> getVenteByPeriode(@PathVariable long id){
        List<LigneVente> ligneVente = ligneVenteService.findDynamicByOneTable("vente","idVente", id);
       return ligneVente.stream().map(ligneVenteMapper::toDto).toList();
    }
}
