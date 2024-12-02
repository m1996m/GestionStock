package com.GestionStock.stock.repository;

import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.email = :value OR u.tel = :value")
    Optional<Users> findByEmailOrTel(@Param("value") String value);

    Optional<Users> findBySlug(String slug);

    void deleteBySlug(String slug);

    List<Users> findByEntreprise(Entreprise optionalEntreprise);
}
