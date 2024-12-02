package com.GestionStock.stock.repository.generic;

import com.GestionStock.stock.generic.GenericDAO;
import com.GestionStock.stock.model.Client;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class ClientDAO extends GenericDAO<Client> {
    public ClientDAO() {
        super(Client.class);
    }
}
