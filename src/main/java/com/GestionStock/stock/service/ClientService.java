package com.GestionStock.stock.service;

import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.repository.ClientRepository;
import com.GestionStock.stock.repository.generic.ClientDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends GenericService<Client,String> {

    public ClientService(ClientDAO clientDAO, ClientRepository clientRepository) {
        super(clientDAO);
    }
}
