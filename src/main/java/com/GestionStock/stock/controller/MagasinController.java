package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.dto.magasin.MagasinResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.service.MagasinService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/magasin")
public class MagasinController extends GenericController<Magasin, String, MagasinDto, MagasinResponseDto> {
    private final MagasinService magasinService;
    private final MagasinMapper magasinMapper;

    public MagasinController(
            GenericService<Magasin, String> genericService, GenericMapper<Magasin, MagasinDto, MagasinResponseDto> genericMapper,
            MagasinService magasinService, MagasinMapper magasinMapper
    ) {
        super(genericService, genericMapper);
        this.magasinService = magasinService;
        this.magasinMapper = magasinMapper;
    }

    @GetMapping("/email/{email}")
    public MagasinResponseDto getMagasinByEmail(@PathVariable String email){
        Magasin magasin = magasinService.findByValue(email, "email");

        return magasinMapper.toResponseDto(magasin);
    }

    @GetMapping("/tel/{tel}")
    public MagasinResponseDto getMagasinByTel(@PathVariable String tel){
        Magasin magasin = magasinService.findByValue(tel, "tel");

        return magasinMapper.toResponseDto(magasin);
    }

    @GetMapping("/ville")
    public List<MagasinResponseDto> getMagasinByVille(@Param("ville") String ville){
        List<Magasin> magasins = magasinService.findListByAttributeName(ville, "ville");

        return magasins.stream().map(magasinMapper::toResponseDto).toList();
    }

    @GetMapping("/entreprise/{id}")
    public List<MagasinResponseDto> getMagasinByEntreprise(@PathVariable Long id){
        List<Magasin> magasins = magasinService.findDynamicByOneTable("entreprise","idEntreprise",id);

        return magasins.stream().map(magasinMapper::toResponseDto).toList();
    }

    @GetMapping("/rayon/{id}")
    public List<MagasinResponseDto> getMagasinByRayon(@PathVariable Long id){
        List<Magasin> magasins = magasinService.findDynamicByOneTable("rayons","idRayon",id);

        return magasins.stream().map(magasinMapper::toResponseDto).toList();
    }

    @GetMapping("/stock/{id}")
    public List<MagasinResponseDto> getMagasinByStock(@PathVariable Long id){
        List<Magasin> magasins = magasinService.findDynamicByOneTable("stocks","idStock",id);

        return magasins.stream().map(magasinMapper::toResponseDto).toList();
    }
}
