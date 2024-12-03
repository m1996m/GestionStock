package com.GestionStock.stock.model;

import com.GestionStock.stock.global.CodeVenteGenerator;
import com.GestionStock.stock.global.SlugGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vente {
    @Id
    @GeneratedValue
    private long idVente;
    private Date dateVente;
    @Column(unique = true, length = 7)
    private String slug;
    @Column(unique = true, updatable = false)
    private String code;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
            this.code = CodeVenteGenerator.generateCode();
        }
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idMagasin")
    private Magasin magasin;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idUser")
    private Users user;

    @JsonManagedReference
    @OneToMany(mappedBy = "vente")
    private Set<LigneVente> ligneVentes;
}
