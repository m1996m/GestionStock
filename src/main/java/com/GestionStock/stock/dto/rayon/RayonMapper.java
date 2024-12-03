package com.GestionStock.stock.dto.rayon;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.service.MagasinService;
import com.GestionStock.stock.service.RayonService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RayonMapper implements GenericMapper<Rayon,RayonDto, RayonResponseDto> {
    private final RayonService rayonService;
    private final MagasinService magasinService;
    private final MagasinMapper magasinMapper;

    public RayonMapper(RayonService rayonService, MagasinService magasinService, MagasinMapper magasinMapper) {
        this.rayonService = rayonService;
        this.magasinService = magasinService;
        this.magasinMapper = magasinMapper;
    }

    @Override
    public RayonDto toDto(Rayon rayon) {

        return RayonDto.builder()
                .idRayon(rayon.getIdRayon())
                .name(rayon.getName())
                .createdAt(rayon.getCreatedAt())
                .description(rayon.getDescription())
                .slug(rayon.getSlug())
                .build();
    }

    @Override
    public RayonResponseDto toResponseDto(Rayon rayon) {
        return RayonResponseDto.builder()
                .idRayon(rayon.getIdRayon())
                .name(rayon.getName())
                .createdAt(rayon.getCreatedAt())
                .description(rayon.getDescription())
                .slug(rayon.getSlug())
                .magasin(magasinMapper.toResponseDto(rayon.getMagasin()))
                //.produits(List.copyOf(rayon.getProduits()))
                .build();
    }

    @Override
    public Rayon toEntity(RayonDto rayonDto) {
        if (rayonDto.getSlug() != null){
            Rayon rayon = rayonService.findByValue(rayonDto.getSlug(), "slug");

            return rayonDto.update(rayonDto, rayon);
        }else{
            Magasin magasin = magasinService.findById(rayonDto.getIdMagasin());

            return rayonDto.create(rayonDto,magasin);
        }
    }
}
