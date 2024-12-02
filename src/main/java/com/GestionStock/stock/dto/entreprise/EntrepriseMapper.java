package com.GestionStock.stock.dto.entreprise;

import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Entreprise;
import org.springframework.stereotype.Component;

@Component
public class EntrepriseMapper implements GenericMapper<Entreprise, EntrepriseDto, EntrepriseResponseDto> {
    public EntrepriseDto toDto(Entreprise entreprise){
        return EntrepriseDto.builder()
                .name(entreprise.getName())
                .address(entreprise.getAddress())
                .tel(entreprise.getTel())
                .idEntreprise(entreprise.getIdEntreprise())
                .email(entreprise.getEmail())
                .slug(entreprise.getSlug())
                .logo(entreprise.getLogo())
                .ville(entreprise.getVille())
                .createdAt(entreprise.getCreatedAt())
                .build();
    }

    @Override
    public EntrepriseResponseDto toResponseDto(Entreprise entreprise) {
        return EntrepriseResponseDto.builder()
                .name(entreprise.getName())
                .address(entreprise.getAddress())
                .tel(entreprise.getTel())
                .idEntreprise(entreprise.getIdEntreprise())
                .email(entreprise.getEmail())
                .slug(entreprise.getSlug())
                .logo(entreprise.getLogo())
                .ville(entreprise.getVille())
                .createdAt(entreprise.getCreatedAt())
                .build();
    }

    @Override
    public Entreprise toEntity(EntrepriseDto entrepriseDto) {
        return null;
    }
}
