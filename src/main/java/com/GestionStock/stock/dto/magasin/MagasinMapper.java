package com.GestionStock.stock.dto.magasin;

import com.GestionStock.stock.dto.entreprise.EntrepriseMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.service.EntrepriseService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MagasinMapper implements GenericMapper<Magasin, MagasinDto, MagasinResponseDto> {
    private final EntrepriseService entrepriseService;
    private final GenericService<Magasin, String> genericService;
    private final EntrepriseMapper entrepriseMapper;

    public MagasinMapper(
            EntrepriseService entrepriseService, GenericService<Magasin, String> genericService, EntrepriseMapper entrepriseMapper
    ) {
        this.entrepriseService = entrepriseService;
        this.genericService = genericService;
        this.entrepriseMapper = entrepriseMapper;
    }

    @Override
    public MagasinDto toDto(Magasin magasin) {
        return MagasinDto.builder()
                .idMagasin(magasin.getIdMagasin())
                .slug(magasin.getSlug())
                .email(magasin.getEmail())
                .tel(magasin.getTel())
                .address(magasin.getAddress())
                .ville(magasin.getVille())
                .name(magasin.getName())
                .idEntreprise(magasin.getEntreprise().getIdEntreprise())
                .createdAt(magasin.getCreatedAt())
                .build();
    }

    @Override
    public MagasinResponseDto toResponseDto(Magasin magasin) {
        return MagasinResponseDto.builder()
                .idMagasin(magasin.getIdMagasin())
                .slug(magasin.getSlug())
                .email(magasin.getEmail())
                .tel(magasin.getTel())
                .address(magasin.getAddress())
                .ville(magasin.getVille())
                .idEntreprise(magasin.getEntreprise().getIdEntreprise())
                .name(magasin.getName())
                .createdAt(magasin.getCreatedAt())
                //.rayons(List.copyOf(magasin.getRayons()))
                //.stocks(List.copyOf(magasin.getStocks()))
                .entreprise(entrepriseMapper.toResponseDto(magasin.getEntreprise()))
                .build();
    }

    @Override
    public Magasin toEntity(MagasinDto magasinDto) {

        if(magasinDto.getSlug() != null){
            Magasin magasin = genericService.findByValue(magasinDto.getSlug(), "slug");

            return magasinDto.update(magasinDto, magasin);
        }else{
            Entreprise entreprise = entrepriseService.findById(magasinDto.getIdEntreprise());

            return magasinDto.create(magasinDto, entreprise);
        }

    }
}
