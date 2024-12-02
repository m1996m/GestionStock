package com.GestionStock.stock.dto.client;

import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.service.EntrepriseService;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements GenericMapper<Client, ClientDto, ClientResponseDto> {

    private final EntrepriseService entrepriseService;
    private final GenericService<Client, String> genericService;

    public ClientMapper(EntrepriseService entrepriseService, GenericService<Client, String> genericService) {
        this.entrepriseService = entrepriseService;
        this.genericService = genericService;
    }

    @Override
    public ClientDto toDto(Client entity) {
        return ClientDto.builder()
                .idClient(entity.getIdClient())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .tel(entity.getTel())
                .address(entity.getAddress())
                .slug(entity.getSlug())
                .genre(entity.getGenre())
                .createdAt(entity.getCreatedAt())
                .idEntreprise(entity.getEntreprise().getIdEntreprise())
                .build();
    }

    @Override
    public ClientResponseDto toResponseDto(Client entity) {
        return ClientResponseDto.builder()
                .idClient(entity.getIdClient())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .tel(entity.getTel())
                .address(entity.getAddress())
                .slug(entity.getSlug())
                .genre(entity.getGenre())
                .createdAt(entity.getCreatedAt())
                .idEntreprise(entity.getEntreprise().getIdEntreprise())
                .build();
    }

    @Override
    public Client toEntity(ClientDto dto) {

        if (dto.getSlug() != null){

            Client client = genericService.findByValue(dto.getSlug(),"slug");

            return dto.update(dto,client);
        }else{

            Entreprise entreprise = entrepriseService.findById(dto.getIdEntreprise());

            return dto.create(dto,entreprise);
        }
    }

}

