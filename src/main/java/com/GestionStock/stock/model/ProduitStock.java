package com.GestionStock.stock.model;

import com.GestionStock.stock.global.SlugGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProduitStock {
    @Id
    @GeneratedValue
    private long idProduitStock;
    private int quantite;
    @Column(unique = true, length = 30)
    private String slug;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idStock")
    private Stock stock;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;
}
