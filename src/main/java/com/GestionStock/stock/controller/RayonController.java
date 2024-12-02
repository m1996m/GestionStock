package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.rayon.RayonDto;
import com.GestionStock.stock.dto.rayon.RayonMapper;
import com.GestionStock.stock.dto.rayon.RayonResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.service.RayonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rayon")
public class RayonController extends GenericController<Rayon, String, RayonDto, RayonResponseDto> {
    private final RayonService rayonService;
    private final RayonMapper rayonMapper;

    public RayonController(
            GenericService<Rayon, String> genericService, GenericMapper<Rayon, RayonDto, RayonResponseDto> genericMapper,
            RayonService rayonService, RayonMapper rayonMapper
    ) {
        super(genericService, genericMapper);
        this.rayonService = rayonService;
        this.rayonMapper = rayonMapper;
    }

    @GetMapping("/magasin/{id}")
    public List<RayonResponseDto> getListRayonByMagasin(@PathVariable Long id){
        List<Rayon> rayons = rayonService.findDynamicByOneTable("magasin","idMagasin",id);

        return rayons.stream().map(rayonMapper::toResponseDto).toList();
    }

}
