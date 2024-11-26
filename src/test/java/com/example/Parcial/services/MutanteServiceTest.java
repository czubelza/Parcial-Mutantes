package com.example.Parcial.services;

import com.example.Parcial.entities.DNA;
import com.example.Parcial.repositories.Mutanterepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class MutanteServiceTest {
    @Mock
    private Mutanterepository mutanterepository;

    @Mock
    private MutanteStatsService mutanteStatsService;

    @InjectMocks
    private MutanteService mutanteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean result = mutanteService.isMutant(dna);
        assertTrue(result);

        ArgumentCaptor<DNA> captor = ArgumentCaptor.forClass(DNA.class);
        verify(mutanterepository).save(captor.capture());

        DNA capturedDNA = captor.getValue();
        assertArrayEquals(dna, capturedDNA.getDna());
        assertTrue(capturedDNA.isMutant());
    }

    @Test
    public void testIsNotMutant() {
        String[] dna = {"ATGCGA", "CCGTAC", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG"};
        boolean result = mutanteService.isMutant(dna);
        assertFalse(result);

        ArgumentCaptor<DNA> captor = ArgumentCaptor.forClass(DNA.class);
        verify(mutanterepository).save(captor.capture());

        DNA capturedDNA = captor.getValue();
        assertArrayEquals(dna, capturedDNA.getDna());
        assertFalse(capturedDNA.isMutant());
    }
    @Test
    public void ArrayNoVacioTest() {
        String[] dna = {"ATGCGA"};
        boolean resultado = mutanteService.arrayVacio(dna);
        assertFalse(resultado);
    }
    @Test
    public void ArrayVacioTest() {
        String[] dna = {};
        boolean resultado = mutanteService.arrayVacio(dna);
        assertTrue(resultado);
    }


    // Si el Array dna es nxn
    @Test
    public void  EsCuadradaTest(){
        String[] dna = {"ATGCGA", "CCGTAC", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.esCuadrada(matrizDNA);
        assertTrue(resultado);
    }

    @Test
    public void  NoEsCuadradaTest(){
        String[] dna = {"ATGCGAT", "CCGTAC", "TTATGT", "CCCTTA", "TCACTG"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.esCuadrada(matrizDNA);
        assertFalse(resultado);
    }

    //Array dna igual a null
    @Test
    public void arrayNullTest() {
        // el arreglo es null
        String[] dna = null;
        assertTrue(mutanteService.arrayNull(dna), "El arreglo debería ser null");
    }

    @Test
    public void arrayNullTest2() {
        // cuando el arreglo está vacío (pero no es null)
        String[] dna = {};
        assertFalse(mutanteService.arrayNull(dna), "El arreglo no debería ser null");
    }

    //
    @Test
    public void  VerificarletrasNoPermitidasTest(){
        // letras no permitidas B y H colocados en el arreglo
        String[] dna = {"ATGGAT", "CCGBAC", "TTAHGT", "CCCTTA", "TCACTG"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.VerificarletrasPermitidas(matrizDNA,null);
        assertFalse(resultado);
    }
    @Test
    public void  VerificarletrasPermitidasTest(){
        String[] dna = {"ATGGAT", "CCGTAC", "TTAAGT", "CCCTTA", "TCACTG"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.VerificarletrasPermitidas(matrizDNA,null);
        assertTrue(resultado);
    }

    @Test
    public void  VerificarletrasPermitidasTest2(){
        //letras que se permiten
        char [] caracteres = {'A','T','G'};
        // arreglo cuya letra "C" no se permite
        String[] dna = {"ATGGAT", "CCGTAC", "TTAAGT", "CCCTTA", "TCACTG"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.VerificarletrasPermitidas(matrizDNA,caracteres);
        assertFalse(resultado);
    }


    @Test
    public void  MatrizNOMayorA3Test(){
        String[] dna = {"ATG", "GTA", "TTT"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.MatrizMayorA3(matrizDNA);
        assertFalse(resultado);
    }

    @Test
    public void  MatrizMayorA3Test(){
        String[] dna = {"ATGC", "GATA", "TCTT","TCGA"};
        char [][] matrizDNA = mutanteService.conversorAMatriz(dna);
        boolean resultado = mutanteService.MatrizMayorA3(matrizDNA);
        assertTrue(resultado);
    }
}
