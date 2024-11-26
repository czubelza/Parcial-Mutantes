package com.example.Parcial.repositories;

import com.example.Parcial.entities.MutanteStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutanteStatsrepository extends JpaRepository<MutanteStats, Long> {

}
