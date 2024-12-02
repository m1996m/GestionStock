package com.GestionStock.stock.model;

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
public class Magasin {
    @Id
    @GeneratedValue
    private long idMagasin;
    private String name;
    private String address;
    @Column(nullable = true)
    private String ville;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String tel;
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
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

    @JsonManagedReference
    @OneToMany(mappedBy = "magasin")
    private Set<Rayon> rayons;

    @JsonManagedReference
    @OneToMany(mappedBy = "magasin")
    private Set<Users> users;

    @JsonManagedReference
    @OneToMany(mappedBy = "magasin")
    private Set<Stock> stocks;

    @JsonManagedReference
    @OneToMany(mappedBy = "magasin")
    private Set<Vente> ventes;
}
