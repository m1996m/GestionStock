package com.GestionStock.stock.dto.emplacementProduit;

import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Emplacement;
import com.GestionStock.stock.model.EmplacementProduit;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.service.EmplacementProduitService;
import com.GestionStock.stock.service.EmplacementService;
import com.GestionStock.stock.service.ProduitService;
import org.springframework.stereotype.Component;

@Component
public class EmplacementProduitMapper implements
        GenericMapper<EmplacementProduit, EmplacementProduitDto, EmplacementProduitResponseDto> {
    private final EmplacementService emplacementService;
    private final ProduitService produitService;
    private final EmplacementProduitService emplacementProduitService;

    public EmplacementProduitMapper(EmplacementService emplacementService, ProduitService produitService, EmplacementProduitService emplacementProduitService) {
        this.emplacementService = emplacementService;
        this.produitService = produitService;
        this.emplacementProduitService = emplacementProduitService;
    }


    @Override
    public EmplacementProduitDto toDto(EmplacementProduit emplacementProduit) {
        return EmplacementProduitDto.builder()
                .idEmplacementProduit(emplacementProduit.getIdEmplacementProduit())
                .slug(emplacementProduit.getSlug())
                .quantite(emplacementProduit.getQuantite())
                .idEmplacement(emplacementProduit.getEmplacement().getIdEmplacement())
                .build();
    }

    @Override
    public EmplacementProduitResponseDto toResponseDto(EmplacementProduit emplacementProduit) {
        return EmplacementProduitResponseDto.builder()
                .idEmplacementProduit(emplacementProduit.getIdEmplacementProduit())
                .slug(emplacementProduit.getSlug())
                .quantite(emplacementProduit.getQuantite())
                .idEmplacement(emplacementProduit.getEmplacement().getIdEmplacement())
                .build();
    }

    @Override
    public EmplacementProduit toEntity(EmplacementProduitDto emplacementProduitDto) {
        Produit produit = produitService.findById(emplacementProduitDto.getIdProduit());
        Emplacement emplacement = emplacementService.findById(emplacementProduitDto.getIdEmplacement());

        if (emplacementProduitDto.getSlug() != null){
            EmplacementProduit emplacementProduit = emplacementProduitService
                    .findByValue(emplacementProduitDto.getSlug(), "slug");

            return emplacementProduitDto.update(emplacementProduitDto, produit,emplacement, emplacementProduit);
        }else {
            return emplacementProduitDto.create(emplacementProduitDto, produit, emplacement);
        }
    }
}
