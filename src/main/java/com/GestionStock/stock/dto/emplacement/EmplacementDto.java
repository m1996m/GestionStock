package com.GestionStock.stock.dto.emplacement;

import com.GestionStock.stock.model.Emplacement;
import com.GestionStock.stock.model.Rayon;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class EmplacementDto {
    private Long idEmplacement;
    private String code;
    private String description;
    private String slug;
    private LocalDateTime createdAt;
    private Long idRayon;

    public Emplacement create(EmplacementDto emplacementDto, Rayon rayon){
        Emplacement emplacement = new Emplacement();
        emplacement.setCode(emplacementDto.getCode());
        emplacement.setDescription(emplacementDto.getDescription());
        emplacement.setRayon(rayon);

        return emplacement;
    }

    public Emplacement update(EmplacementDto emplacementDto, Rayon rayon, Emplacement emplacement){
        emplacement.setCode(emplacementDto.getCode());
        emplacement.setDescription(emplacementDto.getDescription());
        emplacement.setRayon(rayon);

        return emplacement;
    }
}
