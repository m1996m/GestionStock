package com.GestionStock.stock.dto.client;

import com.GestionStock.stock.listEnumeration.Genre;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Entreprise;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ClientDto {
    private long idClient;
    private String email;
    private String firstName;
    private String tel;
    private String lastName;
    private String address;
    private String slug;
    private Genre genre = Genre.AUTRE;
    private LocalDateTime createdAt;
    private long idEntreprise;

    public Client create(ClientDto clientDto, Entreprise entreprise){
        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setTel(clientDto.getTel());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        client.setGenre(clientDto.getGenre());
        client.setEntreprise(entreprise);

        return client;
    }

    public Client update(ClientDto clientDto, Client client){
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setTel(clientDto.getTel());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        client.setGenre(clientDto.getGenre());

        return client;
    }
}
