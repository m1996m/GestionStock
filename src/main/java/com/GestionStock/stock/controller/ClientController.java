package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.client.ClientDto;
import com.GestionStock.stock.dto.client.ClientMapper;
import com.GestionStock.stock.dto.client.ClientResponseDto;
import com.GestionStock.stock.generic.GenericController;
import com.GestionStock.stock.generic.GenericMapper;
import com.GestionStock.stock.generic.GenericService;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.service.ClientService;
import com.GestionStock.stock.service.EntrepriseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController extends GenericController<Client, String, ClientDto, ClientResponseDto> {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(
            GenericService<Client, String> genericService, GenericMapper<Client,
            ClientDto, ClientResponseDto> genericMapper, ClientService clientService, ClientMapper clientMapper
    ) {
        super(genericService, genericMapper);
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @Operation(summary = "Get client by tel")
    @GetMapping("/tel/{tel}")
    public ClientDto getClientByTel(@PathVariable String tel){
        Client client =  clientService.findByValue(tel,"tel");

        return clientMapper.toDto(client);
    }

    @Operation(summary = "Get client by email")
    @GetMapping("/email/{email}")
    public ClientDto getClientByEmail(@PathVariable String email){
        Client client = clientService.findByValue(email,"email");

        return clientMapper.toDto(client);
    }

    @GetMapping("/entreprise/{id}")
    public List<ClientDto> getListClientByEntreprise(@PathVariable Long id){
        List<Client> clients = clientService.findDynamicByOneTable("entreprise","idEntreprise",id);

        return clients.stream().map(clientMapper::toDto).toList();
    }
}
