package com.GestionStock.stock.dto.emplacement;

import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Emplacement;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.service.EmplacementService;
import com.GestionStock.stock.service.RayonService;
import org.springframework.stereotype.Component;

@Component
public class EmplacementMapper implements GenericMapper<Emplacement, EmplacementDto, EmplacementResponseDto> {
    private final EmplacementService emplacementService;
    private final RayonService rayonService;

    public EmplacementMapper(
            EmplacementService emplacementService, RayonService rayonService
    ) {
        this.emplacementService = emplacementService;
        this.rayonService = rayonService;
    }

    @Override
    public EmplacementDto toDto(Emplacement emplacement) {
        return EmplacementDto.builder()
                .idEmplacement(emplacement.getIdEmplacement())
                .code(emplacement.getCode())
                .idRayon(emplacement.getRayon().getIdRayon())
                .slug(emplacement.getSlug())
                .createdAt(emplacement.getCreatedAt())
                .description(emplacement.getDescription())
                .build();
    }

    @Override
    public EmplacementResponseDto toResponseDto(Emplacement emplacement) {
        return EmplacementResponseDto.builder()
                .idEmplacement(emplacement.getIdEmplacement())
                .code(emplacement.getCode())
                .idRayon(emplacement.getRayon().getIdRayon())
                .slug(emplacement.getSlug())
                .createdAt(emplacement.getCreatedAt())
                .description(emplacement.getDescription())
                .build();
    }

    @Override
    public Emplacement toEntity(EmplacementDto emplacementDto) {
        Rayon rayon = rayonService.findById(emplacementDto.getIdRayon());

        if (emplacementDto.getSlug() != null){
            Emplacement emplacement = emplacementService.findByValue(emplacementDto.getSlug(), "slug");

            return emplacementDto.update(emplacementDto, rayon, emplacement);
        }else {
            return emplacementDto.create(emplacementDto, rayon);
        }
    }
}
