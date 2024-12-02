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
public class EmplacementResponseDto {
    private Long idEmplacement;
    private String code;
    private String description;
    private String slug;
    private LocalDateTime createdAt;
    private Long idRayon;
    private Long idProduit;
}
