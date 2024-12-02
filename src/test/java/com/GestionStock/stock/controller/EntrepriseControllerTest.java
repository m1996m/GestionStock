package com.GestionStock.stock.controller;

import com.GestionStock.stock.authJwt.service.JwtService;
import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.service.EntrepriseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntrepriseController.class)
class EntrepriseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntrepriseService entrepriseService;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    void shouldGetListEntreprise() throws Exception {
        Entreprise entreprise = new Entreprise();
        entreprise.setIdEntreprise(1L);
        entreprise.setName("Malick");
        entreprise.setEmail("mm@gmail.com");
        entreprise.setTel("00987");
        entreprise.setAddress("Bambeto");
        entreprise.setSlug("Bambeto");

        when(entrepriseService.findAllEntreprise()).thenReturn(List.of(entreprise));

        mockMvc.perform(get("/api/entreprise/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(entreprise.getName()));
    }

    @Test
    @WithMockUser
    void shouldGetUserById() throws Exception {
        Entreprise entreprise = new Entreprise(
                1L,"Dilalo", "bambeto","conakry","email@gmail.com","0098",
                null,"bambeto",null,null,null,null
        );

        when(entrepriseService.findById(entreprise.getIdEntreprise())).thenReturn(entreprise);

        mockMvc.perform(get("/api/entreprise/{id}/one", entreprise.getIdEntreprise()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(entreprise.getName()));
    }

    @Test
    @WithMockUser
    void shouldGetEntrepriseBySlug() throws Exception {
        Entreprise entreprise = new Entreprise(
                1L, "Dilalo", "bambeto", "conakry", "email@gmail.com", "0098",
                null, "bambeto", null, null, null,null
        );

        when(entrepriseService.findEntrepriseBySlug(entreprise.getSlug())).thenReturn(entreprise);

        // Effectuer la requête GET et vérifier les résultats
        mockMvc.perform(get("/api/entreprise/{slug}", entreprise.getSlug()))  // Passer le slug en tant que paramètre
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(entreprise.getName()));  // Utiliser $.name au lieu de $[0].name si la réponse est un objet unique
    }

    @Test
    @WithMockUser
    void shouldCreateEntreprise() throws Exception {
        EntrepriseDto entreprise = new EntrepriseDto();
        entreprise.setIdEntreprise(1L);
        entreprise.setName("Malick");
        entreprise.setEmail("mm@gmail.com");
        entreprise.setTel("00987");
        entreprise.setAddress("Bambeto");
        entreprise.setSlug("Bambeto");

        when(entrepriseService.create(entreprise)).thenReturn(entreprise.create(entreprise));

        mockMvc.perform(get("/api/entreprise/create"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.name").value(entreprise.getName()));
    }

    @Test
    @WithMockUser
    void shouldUpdateEntreprise() throws Exception {
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setIdEntreprise(1L);
        entrepriseDto.setName("Diallo");
        entrepriseDto.setEmail("mm@gmail.com");
        entrepriseDto.setTel("00987");
        entrepriseDto.setAddress("Bambeto");
        entrepriseDto.setSlug("Bambeto");

        when(entrepriseService.updateEntrepriseBySlug(entrepriseDto.getSlug(),entrepriseDto))
                .thenReturn(entrepriseDto.create(entrepriseDto));

        mockMvc.perform(get("/api/entreprise/create"))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.name").value(entreprise.getName()));
    }

    @Test
    @WithMockUser
    void shouldGetEntrepriseExistFied() throws Exception {
        Entreprise entreprise = new Entreprise(
                1L, "Dilalo", "bambeto", "conakry", "email@gmail.com", "0098",
                null, "bambeto", null, null, null, null
        );

        String field = "email";
        String value = "email@gmail.com";

        when(entrepriseService.existsEntrepriseByField(field, value)).thenReturn(true);

        mockMvc.perform(get("/api/entreprise/exist", entreprise.getSlug()))
                .andExpect(status().isOk());
    }

}