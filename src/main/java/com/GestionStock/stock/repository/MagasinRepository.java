package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MagasinRepository extends JpaRepository<Magasin, Long> {
    List<Magasin> findByEntreprise(Entreprise entreprise);
}
