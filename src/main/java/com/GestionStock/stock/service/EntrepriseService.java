package com.GestionStock.stock.service;

import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.repository.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntrepriseService {
    private final EntrepriseRepository entrepriseRepository;

    public Entreprise create(EntrepriseDto entrepriseDto) {
        Entreprise entreprise = entrepriseDto.create(entrepriseDto);
        try {
            entreprise = entrepriseRepository.save(entreprise);
        }catch (Exception e){
             throw new RuntimeException("Une erreur lié àa l'inscription",e);
        }
        return entreprise;
    }

    public List<Entreprise> findAllEntreprise() {
        return entrepriseRepository.findAll();
    }

    public Entreprise findEntrepriseBySlug(String slug) {
        Optional <Entreprise> optionalEntreprise = entrepriseRepository.findBySlug(slug);

        if(optionalEntreprise.isEmpty()){
            throw new RuntimeException("Une erreur lié à la lecture");
        }

        return optionalEntreprise.get();
    }

    public Entreprise updateEntrepriseBySlug(String slug, EntrepriseDto entrepriseDto) {
        Optional <Entreprise> optionalEntreprise = entrepriseRepository.findBySlug(slug);

        if (optionalEntreprise.isEmpty()){
            throw new RuntimeException("Entreprise n'existe pas");
        }

        Entreprise entrepriseToUpdate = optionalEntreprise.get();

        entrepriseToUpdate.setTel(entrepriseDto.getTel());
        entrepriseToUpdate.setName(entrepriseDto.getName());
        entrepriseToUpdate.setEmail(entrepriseDto.getEmail());
        entrepriseToUpdate.setVille(entrepriseDto.getVille());
        entrepriseToUpdate.setAddress(entrepriseDto.getAddress());

        Entreprise entreprise = new Entreprise();

        try {
            entreprise = entrepriseRepository.save(entrepriseToUpdate);
        }catch (Exception e){
            throw new RuntimeException("Erreur lors de la modification ", e);
        }

        return entreprise;
    }

    public String deleteEntrepriseById(long id) {
        Optional<Entreprise> optionalEntreprise = entrepriseRepository.findById(id);

        if (optionalEntreprise.isEmpty())
            throw new RuntimeException("Entreprise non trouvée");

        Entreprise entrepriseToDelete = optionalEntreprise.get();

        try {
            entrepriseRepository.delete(entrepriseToDelete);
        }catch (Exception e){
            throw new RuntimeException("Une erreur lors de la suppression d'une entreprise",e);
        }

        return "Suppression réussie avec succès";
    }

    public boolean existsEntrepriseByField(String fieldName, String value) {
        Entreprise entreprise = new Entreprise();

        switch (fieldName) {
            case "email":
                entreprise.setEmail(value);
                break;
            case "tel":
                entreprise.setTel(value);
                break;
            default:
                throw new IllegalArgumentException("Champ non valide : " + fieldName);
        }

        Example<Entreprise> example = Example.of(entreprise);
        return entrepriseRepository.exists(example);
    }

    public Entreprise findById(long id) {
        Optional <Entreprise> optionalEntreprise = entrepriseRepository.findById(id);

        if(optionalEntreprise.isEmpty()){
            throw new RuntimeException("Une erreur lié à la lecture");
        }

        return optionalEntreprise.get();
    }
}
