package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.EmplacementProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmplacementProduitRepository extends JpaRepository<EmplacementProduit, Long> {
}
