package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Rayon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long> {

    List<Rayon> findByMagasin(Magasin magasin);
}
