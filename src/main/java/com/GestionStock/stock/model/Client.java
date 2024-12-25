package com.GestionStock.stock.model;

import com.GestionStock.stock.listEnumeration.Genre;
import com.GestionStock.stock.global.SlugGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue
    private long idClient;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String tel;
    @Column(nullable = true)
    private String lastName;
    private String address;
    @Column(unique = true, length = 30)
    private String slug;
    private Genre genre = Genre.AUTRE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    private Set<Vente> ventes;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

}
