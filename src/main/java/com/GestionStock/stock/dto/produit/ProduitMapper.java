package com.GestionStock.stock.dto.produit;

import com.GestionStock.stock.dto.rayon.RayonMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.service.ProduitService;
import com.GestionStock.stock.service.RayonService;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper implements GenericMapper<Produit, ProduitDto, ProduitResponseDto> {
    private final ProduitService produitService;
    private final RayonService rayonService;
    private final RayonMapper rayonMapper;

    public ProduitMapper(ProduitService produitService, RayonService rayonService, RayonMapper rayonMapper) {
        this.produitService = produitService;
        this.rayonService = rayonService;
        this.rayonMapper = rayonMapper;
    }

    @Override
    public ProduitDto toDto(Produit produit) {

        return ProduitDto.builder()
                .idProduit(produit.getIdProduit())
                .description(produit.getDescription())
                .image(produit.getImage())
                .type(produit.getType())
                .name(produit.getName())
                .slug(produit.getSlug())
                .prix(produit.getPrix())
                .createdAt(produit.getCreatedAt())
                .build();
    }

    @Override
    public ProduitResponseDto toResponseDto(Produit produit) {
        return ProduitResponseDto.builder()
                .idProduit(produit.getIdProduit())
                .description(produit.getDescription())
                .image(produit.getImage())
                .type(produit.getType())
                .name(produit.getName())
                .slug(produit.getSlug())
                .prix(produit.getPrix())
                .createdAt(produit.getCreatedAt())
                .rayon(rayonMapper.toDto(produit.getRayon()))
                .build();
    }

    @Override
    public Produit toEntity(ProduitDto produitDto) {
        if (produitDto.getSlug() != null){
            Produit produit = produitService.findByValue(produitDto.getSlug(), "slug");

            return produitDto.update(produitDto, produit);
        }else {
            Rayon rayon = rayonService.findById(produitDto.getIdRayon());

            return produitDto.create(produitDto, rayon);
        }
    }
}
