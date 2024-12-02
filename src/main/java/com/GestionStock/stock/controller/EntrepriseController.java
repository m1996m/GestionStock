package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.entreprise.EntrepriseMapper;
import com.GestionStock.stock.dto.entreprise.EntrepriseResponseDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.service.EntrepriseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/entreprise")
@RestController
@RequiredArgsConstructor
public class EntrepriseController {

    private final EntrepriseService entrepriseService;
    private final EntrepriseMapper entrepriseMapper;

    @Operation(summary = "Creat new entreprise")
    @PostMapping("/create")
    public EntrepriseResponseDto createNewEntriprise(@RequestBody EntrepriseDto entrepriseDto){
        Entreprise entreprise = entrepriseService.create(entrepriseDto);

        return entrepriseMapper.toResponseDto(entreprise);
    }

    @Operation(summary = "Get all entreprise")
    @GetMapping("/list")
    public List<Entreprise> getAllListEntreprise(){

        return entrepriseService.findAllEntreprise();
    }

    @Operation(summary = "Get entreprise by slug")
    @GetMapping("/{slug}")
    public EntrepriseDto getEntrepriseBySlug(@PathVariable String slug){
        Entreprise entreprise = entrepriseService.findEntrepriseBySlug(slug);

        return entrepriseMapper.toDto(entreprise);
    }

    @Operation(summary = "Upadate entreprise by slug")
    @PutMapping("/{slug}")
    public EntrepriseDto updateEntrepriseBySlug(@PathVariable String slug, @RequestBody EntrepriseDto entrepriseDto){
        Entreprise entreprise = entrepriseService.updateEntrepriseBySlug(slug, entrepriseDto);

        return entrepriseMapper.toDto(entreprise);
    }

    @Operation(summary = "get entreprise by id")
    @GetMapping("/{id}/one")
    public EntrepriseDto getEntrepriseById(@PathVariable long id){
        Entreprise entreprise = entrepriseService.findById(id);

        return entrepriseMapper.toDto(entreprise);
    }

    @Operation(summary = "Detele entreprise by slug")
    @DeleteMapping("/{id}")
    public String deleteEntrepriseBySlug(@PathVariable long id){
        return entrepriseService.deleteEntrepriseById(id);
    }

    @Operation(summary = "Very email or tel if existe")
    @GetMapping("/exist")
    public boolean existsEntrepriseByField(@RequestParam("field") String field,
                                           @RequestParam("value") String value){
        return entrepriseService.existsEntrepriseByField(field, value);
    }
}
