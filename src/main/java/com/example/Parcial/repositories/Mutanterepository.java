package com.example.Parcial.repositories;

import com.example.Parcial.entities.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Mutanterepository  extends JpaRepository<DNA, Long> {
    Optional<DNA> findByDna(String[] dna);
    long countByIsMutant(boolean isMutant);

}
