package com.GestionStock.stock.dto.vente;

import com.GestionStock.stock.dto.client.ClientMapper;
import com.GestionStock.stock.dto.ligneVente.LigneVenteMapper;
import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.dto.user.UserMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.*;
import com.GestionStock.stock.service.ClientService;
import com.GestionStock.stock.service.MagasinService;
import com.GestionStock.stock.service.UserService;
import com.GestionStock.stock.service.VenteService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VenteMapper implements GenericMapper<Vente, VenteDto, VenteResponseDto> {
    private final UserService userService;
    private final MagasinService magasinService;
    private final ClientService clientService;
    private final VenteService venteService;
    private final ClientMapper clientMapper;
    private final MagasinMapper magasinMapper;
    private final UserMapper userDto;

    public VenteMapper(
            UserService userService, MagasinService magasinService, ClientService clientService,
            VenteService venteService, ClientMapper clientMapper, MagasinMapper magasinMapper, UserMapper userDto
    ) {
        this.userService = userService;
        this.magasinService = magasinService;
        this.clientService = clientService;
        this.venteService = venteService;
        this.clientMapper = clientMapper;
        this.magasinMapper = magasinMapper;
        this.userDto = userDto;
    }

    @Override
    public VenteDto toDto(Vente vente) {
        return VenteDto.builder()
                .idVente(vente.getIdVente())
                .slug(vente.getSlug())
                .createdAt(vente.getCreatedAt())
                .dateVente(vente.getDateVente())
                .build();
    }

    @Override
    public VenteResponseDto toResponseDto(Vente vente) {
        return VenteResponseDto.builder()
                .idVente(vente.getIdVente())
                .slug(vente.getSlug())
                .createdAt(vente.getCreatedAt())
                .dateVente(vente.getDateVente())
                .client(clientMapper.toResponseDto(vente.getClient()))
                .magasin(magasinMapper.toResponseDto(vente.getMagasin()))
                .user(userDto.toResponseDto(vente.getUser()))
                //.ligneVentes(List.copyOf(vente.getLigneVentes()))
                .build();
    }

    @Override
    public Vente toEntity(VenteDto venteDto) {
        Client client = clientService.findById(venteDto.getIdClient());

        if (venteDto.getSlug() != null){
            Vente vente = venteService.findByValue(venteDto.getSlug(), "slug");

            return  venteDto.update(venteDto, client, vente);
        }else{
            Users user = userService.findUserById(venteDto.getIdUser());
            Magasin magasin = magasinService.findById(venteDto.getIdMagasin());

            return venteDto.create(venteDto, client, magasin, user);
        }
    }
}
