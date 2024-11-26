package com.example.Parcial.services;

import com.example.Parcial.entities.MutanteStats;
import com.example.Parcial.repositories.MutanteStatsrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
class MutanteStatsServiceTest {
    @Mock
    private MutanteStatsrepository mutanteStatsRepository;

    @InjectMocks
    private MutanteStatsService mutanteStatsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStats() {
        MutanteStats stats = mutanteStatsService.getStats();
        assertNotNull(stats);
        assertEquals(0, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio(), 0.001);
    }

    @Test
    public void testUpdateStatsMutant() {
        mutanteStatsService.updateStats(true);
        MutanteStats stats = mutanteStatsService.getStats();
        assertEquals(1, stats.getCountMutantDna());
        //assertEquals(0, stats.getCountHumanDna());
        //assertEquals(1.0, stats.getRatio(), 0.001);
    }

    @Test
    public void testUpdateStatsHuman() {
        mutanteStatsService.updateStats(false);
        MutanteStats stats = mutanteStatsService.getStats();
        assertEquals(0, stats.getCountMutantDna());
        assertEquals(1, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio(), 0.001);

    }

}