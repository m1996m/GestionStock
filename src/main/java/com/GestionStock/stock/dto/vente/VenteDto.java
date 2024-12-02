package com.GestionStock.stock.dto.vente;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Client;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.model.Vente;
import com.GestionStock.stock.repository.generic.ClientDAO;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class VenteDto {
    private long idVente;
    private Date dateVente;
    private String slug;
    private LocalDateTime createdAt;
    private long idClient;
    private long idMagasin;
    private long idUser;

    public Vente create(VenteDto venteDto, Client client, Magasin magasin, Users users){
        Vente vente = new Vente();
        vente.setDateVente(venteDto.getDateVente());
        vente.setClient(client);
        vente.setMagasin(magasin);
        vente.setUser(users);

        return vente;
    }

    public Vente update(VenteDto venteDto, Client client, Vente vente){
        vente.setDateVente(venteDto.getDateVente());
        vente.setClient(client);

        return vente;
    }
}
