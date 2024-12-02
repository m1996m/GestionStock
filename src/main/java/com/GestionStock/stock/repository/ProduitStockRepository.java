package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.ProduitStock;
import com.GestionStock.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitStockRepository extends JpaRepository<ProduitStock, Long> {
}
