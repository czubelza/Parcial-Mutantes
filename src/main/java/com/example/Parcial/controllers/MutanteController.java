package com.example.Parcial.controllers;

import com.example.Parcial.DTO.DTOdna;
import com.example.Parcial.services.MutanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutanteController {
    @Autowired
    private MutanteService mutanteService;

    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody DTOdna dnaRequest) {
        boolean isMutant = mutanteService.isMutant(dnaRequest.getDna());
        return isMutant ? ResponseEntity.ok("ADN MUTANTE ENCONTRADO.") : ResponseEntity.status(HttpStatus.FORBIDDEN).body("ADN MUTANTE NO ENCONTRADO");
    }

}
