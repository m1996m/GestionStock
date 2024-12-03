package com.GestionStock.stock.dto.user;

import com.GestionStock.stock.dto.entreprise.EntrepriseMapper;
import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements GenericMapper<Users, UserDto, UserResponseDto> {
    private final MagasinMapper magasinMapper;
    private final EntrepriseMapper entrepriseDto;

    public UserMapper(MagasinMapper magasinMapper, EntrepriseMapper entrepriseDto) {
        this.magasinMapper = magasinMapper;
        this.entrepriseDto = entrepriseDto;
    }


    @Override
    public UserDto toDto(Users users){
        return UserDto.builder()
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .address(users.getAddress())
                .avatar(users.getAvatar())
                .idUser(users.getIdUser())
                .gender(users.getGender())
                .role(users.getRole())
                .createdAt(users.getCreatedAt())
                .build();
    }

    @Override
    public UserResponseDto toResponseDto(Users users) {
        return UserResponseDto.builder()
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .address(users.getAddress())
                .avatar(users.getAvatar())
                .idUser(users.getIdUser())
                .gender(users.getGender())
                .role(users.getRole())
                .createdAt(users.getCreatedAt())
                .entreprise(entrepriseDto.toDto(users.getEntreprise()))
                .magasin(magasinMapper.toResponseDto(users.getMagasin()))
                .ventes(List.copyOf(users.getVentes()))
                .build();
    }

    @Override
    public Users toEntity(UserDto userDto) {
        return null;
    }
}
