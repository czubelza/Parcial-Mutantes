package com.example.Parcial.services;

import com.example.Parcial.entities.MutanteStats;
import com.example.Parcial.repositories.MutanteStatsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutanteStatsService {
    @Autowired
    private MutanteStatsrepository mutanteStatsRepository;

    public MutanteStats getStats() {
        MutanteStats stats = mutanteStatsRepository.findById(1L).orElseGet(() -> new MutanteStats());
        return stats;
    }

    public void updateStats(boolean isMutant) {
        MutanteStats stats = mutanteStatsRepository.findById(1L).orElseGet(() -> new MutanteStats());
        if (isMutant) {
            stats.setCountMutantDna(stats.getCountMutantDna() + 1);
        } else {
            stats.setCountHumanDna(stats.getCountHumanDna() + 1);
        }
        stats.setRatio((double) stats.getCountMutantDna() / (stats.getCountMutantDna() + stats.getCountHumanDna()));
        mutanteStatsRepository.save(stats);
    }
}
