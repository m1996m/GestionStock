package com.GestionStock.stock.model;

import com.GestionStock.stock.global.SlugGenerator;
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
public class Entreprise {
    @Id
    @GeneratedValue
    private long idEntreprise;
    private String name;
    private String address;
    @Column(nullable = true)
    private String ville;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String tel;
    @Column(nullable = true)
    private String logo;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "entreprise")
    private Set<Users> user;

    @JsonManagedReference
    @OneToMany(mappedBy = "entreprise")
    private Set<Magasin> magasins;

    @JsonManagedReference
    @OneToMany(mappedBy = "entreprise")
    private Set<Client> clients;

}
