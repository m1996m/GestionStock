package com.GestionStock.stock.service;

import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.repository.EntrepriseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntrepriseServiceTest {
    @Mock
    private EntrepriseRepository entrepriseRepository;

    @InjectMocks
    private EntrepriseService entrepriseService;

    @Test
    public void ShouldCreateEntreprise(){
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setIdEntreprise(1L);
        entrepriseDto.setName("Malick");
        entrepriseDto.setEmail("mm@gmail.com");
        entrepriseDto.setTel("00987");
        entrepriseDto.setAddress("Bambeto");

        Entreprise entreprise = entrepriseDto.create(entrepriseDto);

        when(entrepriseRepository.save(any(Entreprise.class))).thenReturn(entreprise);

        Entreprise entrepriseToTest = entrepriseService.create(entrepriseDto);

        assertEquals("Malick",entrepriseToTest.getName());

        verify(entrepriseRepository, times(1)).save(any(Entreprise.class));
    }

    @Test
    public void shouldFindAllEntreprise(){
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setIdEntreprise(1L);
        entrepriseDto.setName("Malick");
        entrepriseDto.setEmail("mm@gmail.com");
        entrepriseDto.setTel("00987");
        entrepriseDto.setAddress("Bambeto");

        when(entrepriseRepository.findAll()).thenReturn(List.of(entrepriseDto.create(entrepriseDto)));

        List<Entreprise> entreprise = entrepriseService.findAllEntreprise();

        assertEquals("Malick", entreprise.get(0).getName());

        verify(entrepriseRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindAllEntrepriseById(){
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setIdEntreprise(1L);
        entrepriseDto.setName("Malick");
        entrepriseDto.setEmail("mm@gmail.com");
        entrepriseDto.setTel("00987");
        entrepriseDto.setAddress("Bambeto");

        when(entrepriseRepository.findById(entrepriseDto.getIdEntreprise())).thenReturn(Optional.of(entrepriseDto.create(entrepriseDto)));

        Entreprise entreprise = entrepriseService.findById(entrepriseDto.getIdEntreprise());

        assertEquals("Malick", entreprise.getName());

        verify(entrepriseRepository, times(1)).findById(entrepriseDto.getIdEntreprise());
    }

    @Test
    public void shouldFindAllEntrepriseBySlug(){
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setIdEntreprise(1L);
        entrepriseDto.setName("Malick");
        entrepriseDto.setEmail("mm@gmail.com");
        entrepriseDto.setTel("00987");
        entrepriseDto.setAddress("Bambeto");
        entrepriseDto.setSlug("Bambeto");

        when(entrepriseRepository.findBySlug(entrepriseDto.getSlug())).thenReturn(Optional.of(entrepriseDto.create(entrepriseDto)));

        Entreprise entreprise = entrepriseService.findEntrepriseBySlug(entrepriseDto.getSlug());

        assertEquals("Malick", entreprise.getName());

        verify(entrepriseRepository, times(1)).findBySlug(entrepriseDto.getSlug());
    }

    @Test
    public void shouldUpdateBySlug(){
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setIdEntreprise(1L);
        entrepriseDto.setName("Malicki");
        entrepriseDto.setEmail("mm@gmail.com");
        entrepriseDto.setTel("00987");
        entrepriseDto.setAddress("Bambeto");
        entrepriseDto.setSlug("Bambeto");

        Entreprise entreprise = new Entreprise();
        entreprise.setIdEntreprise(1L);
        entreprise.setName("Malick");
        entreprise.setEmail("mm@gmail.com");
        entreprise.setTel("00987");
        entreprise.setAddress("Bambeto");
        entreprise.setSlug("Bambeto");

        when(entrepriseRepository.findBySlug(entreprise.getSlug())).thenReturn(Optional.of(entreprise));

        when(entrepriseRepository.save(any(Entreprise.class))).thenReturn(entrepriseDto.create(entrepriseDto));

        Entreprise entreprise1 = entrepriseService.updateEntrepriseBySlug("Bambeto",entrepriseDto);

        assertEquals("Malicki", entreprise1.getName());

        verify(entrepriseRepository, times(1)).save(any(Entreprise.class));
    }

    @Test
    public void shouldDeleteBySlug(){
        long idEntreprise = 1L;

        lenient().doThrow(new RuntimeException("Entreprise non trouvée")).when(entrepriseRepository).deleteById(idEntreprise);

        assertThrows(RuntimeException.class, () -> entrepriseService.deleteEntrepriseById(idEntreprise));

        verify(entrepriseRepository, never()).deleteById(idEntreprise);
    }

    @Test
    public void shouldEntrepriseExistByFieldAndEmail(){
        Entreprise entreprise = new Entreprise();
        entreprise.setIdEntreprise(1L);
        entreprise.setName("Malick");
        entreprise.setEmail("mm@gmail.com");
        entreprise.setTel("00987");
        entreprise.setAddress("Bambeto");
        entreprise.setSlug("Bambeto");

        String value = "mm@gmail.com";
        String field = "email";

        Example <Entreprise> example = Example.of(entreprise);

        when(entrepriseRepository.exists(any(Example.class))).thenReturn(true);

        boolean exists = entrepriseService.existsEntrepriseByField(field,value);

        assertTrue(exists);

        verify(entrepriseRepository, times(1)).exists(any(Example.class));
    }


}