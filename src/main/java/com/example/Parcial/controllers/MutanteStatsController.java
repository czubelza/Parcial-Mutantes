package com.example.Parcial.controllers;

import com.example.Parcial.entities.MutanteStats;
import com.example.Parcial.services.MutanteStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class MutanteStatsController {
    @Autowired
    private MutanteStatsService mutanteStatsService;

    @GetMapping
    public ResponseEntity<MutanteStats> getStats() {
        MutanteStats stats = mutanteStatsService.getStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
