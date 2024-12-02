package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.emplacementProduit.EmplacementProduitDto;
import com.GestionStock.stock.dto.emplacementProduit.EmplacementProduitResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.EmplacementProduit;
import com.GestionStock.stock.model.Produit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emplacement/produit")
public class EmplacementProduitController extends
        GenericController<EmplacementProduit, String, EmplacementProduitDto, EmplacementProduitResponseDto> {
    public EmplacementProduitController(
            GenericService<EmplacementProduit, String> genericService,
            GenericMapper<EmplacementProduit, EmplacementProduitDto, EmplacementProduitResponseDto> genericMapper
    ) {
        super(genericService, genericMapper);
    }
}
