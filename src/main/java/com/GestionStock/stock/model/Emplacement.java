package com.GestionStock.stock.model;

import com.GestionStock.stock.global.CodeGenerator;
import com.GestionStock.stock.global.SlugGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Emplacement {
    @Id
    @GeneratedValue
    private Long idEmplacement;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false, unique = true)
    private String slug;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idRayon")
    private Rayon rayon;

    @JsonManagedReference
    @OneToMany(mappedBy = "emplacement")
    private Set<EmplacementProduit> emplacementProduits;

    @PrePersist
    public void generatedSlugBeforePersist(){
        if (this.slug == null || this.slug.isEmpty()){
            this.slug = SlugGenerator.generateSlug();
            this.code = CodeGenerator.generateCode();
        }
    }
}
