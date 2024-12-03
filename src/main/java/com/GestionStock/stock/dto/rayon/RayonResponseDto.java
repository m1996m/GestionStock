package com.GestionStock.stock.dto.rayon;

import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.magasin.MagasinResponseDto;
import com.GestionStock.stock.dto.produit.ProduitDto;
import com.GestionStock.stock.model.Magasin;
import com.GestionStock.stock.model.Produit;
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
public class RayonResponseDto {
    private long idRayon;
    private String name;
    private String description;
    private String slug;
    private LocalDateTime createdAt;
    private Long idMagasin;
    private MagasinResponseDto magasin;
    //private List<Produit> produits;
}
