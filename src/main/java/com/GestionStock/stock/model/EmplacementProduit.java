package com.GestionStock.stock.model;

import com.GestionStock.stock.global.SlugGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmplacementProduit {
    @Id
    @GeneratedValue
    private Long idEmplacementProduit;
    private String slug;
    private long quantite;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idProduit")
    private Produit produit;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idEmplacement")
    private Emplacement emplacement;

    @PrePersist
    public void generatedSlugBeforePersist(){
        if (this.slug == null || this.slug.isEmpty()){
            this.slug = SlugGenerator.generateSlug();
        }
    }
}
