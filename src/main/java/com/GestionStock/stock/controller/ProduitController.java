package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.produit.ProduitDto;
import com.GestionStock.stock.dto.produit.ProduitMapper;
import com.GestionStock.stock.dto.produit.ProduitResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.service.ProduitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitController extends GenericController<Produit, String, ProduitDto, ProduitResponseDto> {
    private final ProduitService produitService;
    private final ProduitMapper produitMapper;

    public ProduitController(GenericService<Produit, String> genericService, GenericMapper<Produit,
            ProduitDto, ProduitResponseDto> genericMapper, ProduitService produitService, ProduitMapper produitMapper
    ) {
        super(genericService, genericMapper);
        this.produitService = produitService;
        this.produitMapper = produitMapper;
    }


    @GetMapping("/rayon/{id}")
    public List<ProduitResponseDto> getListProduitByRayon(@PathVariable Long id){
        List<Produit> produits = produitService.findDynamicByOneTable("rayon","idRayon",id);

        return produits.stream().map(produitMapper::toResponseDto).toList();
    }

    @GetMapping("/code/{code}")
    public ProduitResponseDto getListProduitByCode(@PathVariable String code){
        Produit produit = produitService.findByValue(code,"code");

        return produitMapper.toResponseDto(produit);
    }

    @GetMapping("/{id}/stock")
    public List<ProduitResponseDto> getListProduitByStock(@PathVariable long id){
        List<Produit> produits = produitService.findDynamicJoinTable("produitStocks", "stock","idStock", id);

        return produits.stream().map(produitMapper::toResponseDto).toList();
    }

    @GetMapping("/emplacement/{id}")
    public List<ProduitResponseDto> getListProduitByEmplacement(@PathVariable long id){
        List<Produit> produits = produitService.findDynamicJoinTable(
                "emplacementProduits",
                "emplacement",
                "idEmplacement", id
        );

        return produits.stream().map(produitMapper::toResponseDto).toList();
    }
}
