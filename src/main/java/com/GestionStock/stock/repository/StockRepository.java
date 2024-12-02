package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Rayon;
import com.GestionStock.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByMagasin(Magasin magasin);
}
