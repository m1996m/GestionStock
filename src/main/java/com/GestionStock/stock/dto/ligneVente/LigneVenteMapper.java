package com.GestionStock.stock.dto.ligneVente;

import com.GestionStock.stock.dto.produit.ProduitMapper;
import com.GestionStock.stock.dto.vente.VenteMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.LigneVente;
import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Vente;
import com.GestionStock.stock.service.LigneVenteService;
import com.GestionStock.stock.service.ProduitService;
import com.GestionStock.stock.service.VenteService;
import org.springframework.stereotype.Component;

@Component
public class LigneVenteMapper implements GenericMapper<LigneVente, LigneVenteDto, LigneVenteResponseDto> {
    private final VenteService venteService;
    private final LigneVenteService ligneVenteService;
    private final ProduitService produitService;
    private final ProduitMapper produitMapper;
    private final VenteMapper venteMapper;

    public LigneVenteMapper(
            VenteService venteService, LigneVenteService ligneVenteService, ProduitService produitService,
            ProduitMapper produitMapper, VenteMapper venteMapper
    ) {
        this.venteService = venteService;
        this.ligneVenteService = ligneVenteService;
        this.produitService = produitService;
        this.produitMapper = produitMapper;
        this.venteMapper = venteMapper;
    }

    @Override
    public LigneVenteDto toDto(LigneVente ligneVente) {
        return LigneVenteDto.builder()
                .idLigneVente(ligneVente.getIdLigneVente())
                .puv(ligneVente.getPuv())
                .createdAt(ligneVente.getCreatedAt())
                .quantite(ligneVente.getQuantite())
                .slug(ligneVente.getSlug())
                .build();
    }

    @Override
    public LigneVenteResponseDto toResponseDto(LigneVente ligneVente) {
        return LigneVenteResponseDto.builder()
                .idLigneVente(ligneVente.getIdLigneVente())
                .vente(venteMapper.toResponseDto(ligneVente.getVente()))
                .puv(ligneVente.getPuv())
                .createdAt(ligneVente.getCreatedAt())
                .produit(produitMapper.toResponseDto(ligneVente.getProduit()))
                .quantite(ligneVente.getQuantite())
                .idProduit(ligneVente.getProduit().getIdProduit())
                .idVente(ligneVente.getVente().getIdVente())
                .slug(ligneVente.getSlug())
                .build();
    }

    @Override
    public LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        Produit produit = produitService.findById(ligneVenteDto.getIdProduit());
        Vente vente = venteService.findById(ligneVenteDto.getIdVente());

        if (ligneVenteDto.getSlug() != null){
            LigneVente ligneVente = ligneVenteService.findByValue(ligneVenteDto.getSlug(), "slug");

            return ligneVenteDto.update(ligneVenteDto, vente, produit, ligneVente);
        }else {
            return ligneVenteDto.create(ligneVenteDto, vente, produit);
        }
    }
}
