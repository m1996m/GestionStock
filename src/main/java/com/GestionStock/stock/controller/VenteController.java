package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.dto.vente.VenteMapper;
import com.GestionStock.stock.dto.vente.VenteResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Vente;
import com.GestionStock.stock.service.VenteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vente")
public class VenteController extends GenericController<Vente, String, VenteDto, VenteResponseDto> {

    private final VenteService venteService;
    private final VenteMapper venteMapper;

    public VenteController(
            GenericService<Vente, String> genericService, GenericMapper<Vente, VenteDto, VenteResponseDto> genericMapper,
            VenteService venteService, VenteMapper venteMapper
    ) {
        super(genericService, genericMapper);
        this.venteService = venteService;
        this.venteMapper = venteMapper;
    }

    @GetMapping("/magasin/{id}")
    public List<VenteResponseDto> getVenteByMagasin(@PathVariable Long id){
        List<Vente> ventes = venteService.findDynamicByOneTable("magasin","idMagasin",id);

        return ventes.stream().map(venteMapper::toResponseDto).toList();
    }

    @GetMapping("/client/{id}")
    public List<VenteResponseDto> getVenteByClient(@PathVariable Long id){
        List<Vente> ventes = venteService.findDynamicByOneTable("client","idClient",id);;

        return ventes.stream().map(venteMapper::toResponseDto).toList();
    }

    @GetMapping("/periode/{dateVente}")
    public List<VenteResponseDto> getVenteByPeriode(@PathVariable Date dateVente){
        List<Vente> ventes = venteService.findListByAttributeName(dateVente,"dateVente");

        return ventes.stream().map(venteMapper::toResponseDto).toList();
    }
}
