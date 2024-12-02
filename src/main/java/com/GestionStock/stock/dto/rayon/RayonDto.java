package com.GestionStock.stock.dto.rayon;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.produit.ProduitDto;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Rayon;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class RayonDto {
    private long idRayon;
    private String name;
    private String description;
    private String slug;
    private LocalDateTime createdAt;
    private Long idMagasin;

    public Rayon create(RayonDto rayonDto, Magasin magasin){

        Rayon rayon = new Rayon();
        rayon.setName(rayonDto.getName());
        rayon.setDescription(rayonDto.getDescription());
        rayon.setMagasin(magasin);

        return rayon;
    }

    public Rayon update(RayonDto rayonDto, Rayon rayon){

        rayon.setName(rayonDto.getName());
        rayon.setDescription(rayonDto.getDescription());

        return rayon;
    }
}
