package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.Produit;
import com.GestionStock.stock.model.Rayon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    @Query(
            "SELECT p FROM Produit p JOIN p.emplacementProduits ep WHERE ep.emplacement.idEmplacement = :idEmplacement"
    )
    List<Produit> findByEmplacementId(@Param("idEmplacement") Long idEmplacement);
}
