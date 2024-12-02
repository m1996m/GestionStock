package com.GestionStock.stock.generic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GenericController<Entity, Type, Dto, ResponseDto> {
    private final GenericService<Entity,Type> genericService;
    private final GenericMapper<Entity, Dto,ResponseDto> genericMapper;

    public GenericController(
            GenericService<Entity, Type> genericService, GenericMapper<Entity,Dto,ResponseDto> genericMapper
    ) {
        this.genericService = genericService;
        this.genericMapper = genericMapper;
    }

    @GetMapping("/list")
    public List<ResponseDto> getAll(){
        List<Entity>  entities = genericService.findAll();

        return entities.stream().map(genericMapper::toResponseDto).toList();
    }

    @GetMapping("/{slug}")
    public ResponseDto getBySlug(@PathVariable("slug") Type slug){
        Entity entity = genericService.findByValue(slug, "slug");

        return genericMapper.toResponseDto(entity);
    }

    @GetMapping("/id/{id}")
    public ResponseDto getById(@PathVariable("id") long id){
        Entity entity = genericService.findById(id);

        return genericMapper.toResponseDto(entity);
    }

    @DeleteMapping("/{slug}")
    public String deleteBySlug(@PathVariable Type slug){
        return genericService.delete(slug);
    }

    @PostMapping("/create")
    public ResponseDto create(@RequestBody Dto dto){
        Entity entity = genericMapper.toEntity(dto);

        Entity entitySave = genericService.create(entity);

        return genericMapper.toResponseDto(entitySave);
    }

    @PutMapping("/update/{slug}")
    public ResponseDto updateBySlug(@PathVariable("slug") Type slug, @RequestBody Dto dto){
        Entity entity = genericMapper.toEntity(dto);

        Entity entityUpdate = genericService.update(entity,slug);

        return genericMapper.toResponseDto(entityUpdate);
    }

}
