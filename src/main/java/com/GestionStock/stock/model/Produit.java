package com.GestionStock.stock.model;

import com.GestionStock.stock.global.CodeGenerator;
import com.GestionStock.stock.global.CodeProduitGenerator;
import com.GestionStock.stock.global.SlugGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produit {
    @Id
    @GeneratedValue
    private long idProduit;
    private String name;
    @Column(updatable = false, unique = true)
    private String code;
    @Column(nullable = true)
    private String image;
    private double prix;
    private String description;
    private String type;
    @Column(unique = true, length = 30)
    private String slug;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
        this.code = CodeProduitGenerator.generateCombination(name,type);
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idRayon")
    private Rayon rayon;

    @JsonManagedReference
    @OneToMany(mappedBy = "produit")
    private Set<ProduitStock> produitStocks;

    @JsonManagedReference
    @OneToMany(mappedBy = "produit")
    private Set<LigneVente> ligneVentes;

    @JsonManagedReference
    @OneToMany(mappedBy = "produit")
    private Set<EmplacementProduit> emplacementProduits;
}
