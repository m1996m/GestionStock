package com.GestionStock.stock.generic;

public interface GenericMapper<Entity, Dto, ResponseDto> {
    Dto toDto(Entity entity);
    ResponseDto toResponseDto(Entity entity);
    Entity toEntity(Dto dto);       // Convertir un DTO en entité
}
