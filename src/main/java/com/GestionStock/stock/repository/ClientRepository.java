package com.GestionStock.stock.repository;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByEntreprise(Entreprise entreprise);
}
