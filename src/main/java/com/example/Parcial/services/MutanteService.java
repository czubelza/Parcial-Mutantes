package com.example.Parcial.services;


import com.example.Parcial.entities.DNA;
import com.example.Parcial.repositories.Mutanterepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MutanteService {
    @Autowired
    private Mutanterepository mutanterepository;
    @Autowired
    private MutanteStatsService mutanteStatsService;

    public boolean isMutant(String[] dna) {
        char[] LetrasPermitidas = {'A','T','C','G'};
        char[][] matrizdeChar = conversorAMatriz(dna);
        boolean esMutante = false;

        for (int i = 0; i < LetrasPermitidas.length; i++ ){
            if( MutanteSecuenciaHorizontal(LetrasPermitidas[i],matrizdeChar) ){
                esMutante = true;
            }
            if (  MutanteSecuenciaVertical(LetrasPermitidas[i],matrizdeChar) ){
                esMutante = true;
            }
            if ( MutanteSecuenciaDiagonal(matrizdeChar, LetrasPermitidas[i]) ){
                esMutante= true;

            }

        }

        persistDNA(dna, esMutante);
        //problema acá
        mutanteStatsService.updateStats(esMutante);
        return esMutante;
    }

    private boolean MutanteSecuenciaHorizontal(char Letra, char[][] filadna ) {
        boolean seRepite = false;
        boolean seRepitesecuencia = false;
        int contadorSecuencia = 0;
        for (int j = 0; j < filadna.length; ++j){
            for(int i = 0; i < filadna.length; ++i) {
                if (i == 0 && contadorSecuencia != 0) {
                    contadorSecuencia = 0;
                }
                if (Letra == filadna[j][i]) {
                    contadorSecuencia++;
                    if (!seRepite) {
                        seRepite = true;
                    }


                    if (contadorSecuencia >= 4) {
                        seRepitesecuencia = true;
                    }
                } else {
                    seRepite = false;
                    contadorSecuencia = 0;
                }

            }


        }
        return seRepitesecuencia;
    }
    private boolean MutanteSecuenciaVertical(char Letra, char[][] matrizDna) {
        boolean seRepite = false;
        boolean seRepitesecuencia = false;
        int contadorSecuencia = 0;
        for(int j = 0; j < matrizDna.length; ++j) {
            for(int k = 0; k < matrizDna.length; ++k) {
                if (k == 0 && contadorSecuencia != 0) {
                    contadorSecuencia = 0;
                }

                if (Letra == matrizDna[k][j]) {
                    ++contadorSecuencia;
                    if (!seRepite) {
                        seRepite = true;
                    }

                    if (contadorSecuencia >= 4) {
                        seRepitesecuencia = true;

                    }
                } else {
                    seRepite = false;
                    contadorSecuencia = 0;
                }
            }


        }
        return seRepitesecuencia;

    }
    private boolean MutanteSecuenciaDiagonal(char[][] matrizDna, char Letra){
        int contadorSecuencia = 0;
        boolean seRepite = false;
        boolean seRepitesecuencia = false;

        //parte superior diagonal

        int  n = 0;
        int m  = matrizDna.length;

        while( m > 3){
            for(int j = 0; j < m; j++){
                if (j == 0 && contadorSecuencia != 0) {
                    contadorSecuencia = 0;
                }
                if (Letra == matrizDna[j][j+n]) {
                    ++contadorSecuencia;
                    if (!seRepite) {
                        seRepite = true;
                    }

                    if (contadorSecuencia >= 4) {
                        seRepitesecuencia = true;

                    }
                } else {
                    seRepite = false;
                    contadorSecuencia = 0;
                }
            }

            n++;
            m--;

        }

        //parte inferior diagonal

        n = 0;
        m = matrizDna.length;

        while( m > 3){
            for(int j = 0; j < m; j++){
                if (j == 0 && contadorSecuencia != 0) {
                    contadorSecuencia = 0;
                }
                if (Letra == matrizDna[j+n][j]) {
                    ++contadorSecuencia;
                    if (!seRepite) {
                        seRepite = true;
                    }

                    if (contadorSecuencia >= 4) {
                        seRepitesecuencia = true;

                    }
                } else {
                    seRepite = false;
                    contadorSecuencia = 0;
                }
            }

            n++;
            m--;
        }

        return seRepitesecuencia;
    }

    private void persistDNA(String[] dna, boolean isMutant) {
        DNA entity = new DNA();
        entity.setDna(dna);
        entity.setMutant(isMutant);
        mutanterepository.save(entity);
    }
    public char[][] conversorAMatriz(String[] cadena){
            // Creamos una matriz de caracteres con el mismo número de filas que el arreglo de cadenas
            char[][] matriz = new char[cadena.length][];

            // Recorremos el arreglo de cadenas
            for (int i = 0; i < cadena.length; i++) {
                // Convertimos cada cadena a un arreglo de caracteres
                matriz[i] = cadena[i].toCharArray();
            }

            return matriz;

    }

    public boolean arrayVacio(String[] dna){

        return  (dna.length == 0) ? true : false ;
    }

    public boolean esCuadrada(char[][] Matri){
        for (int i = 0; i < Matri.length ; i++){
            if (Matri[i].length != Matri.length){
                return false;
            }
        }
        return true;
    }

    //verificar matriz cuya longitud sea mayor a 3X3 o sea de 4X4 para arriba
    public boolean MatrizMayorA3(char[][] Matri){
        int contador = 0;
        if(Matri.length >= 4){
            System.out.println("matri.lenght : " + Matri.length);
            for (int i = 0; i < Matri.length ; i++){
                System.out.println(i);
                System.out.println("Matri[i].length : " + Matri[i].length);
                if (Matri[i].length == Matri.length){
                    contador++;
                }
                System.out.println("contador : " + contador);
            }
            if(contador == Matri.length ){
                return true;
            }else {
                return false;
            }

        }else {
            return false;
        }
    }
    public boolean arrayNull(String[] dna){

        return  (dna == null) ? true : false ;
    }
    public boolean VerificarletrasPermitidas(char[][] Matriz, char [] arrayLetras){
        char[] LetrasPermitidas;
        if(arrayLetras == null){
            //letras predeterminadas de dna
            LetrasPermitidas = new char[]{'A', 'T', 'C', 'G'};
        }else {
            LetrasPermitidas = arrayLetras;
        }
        for (int i = 0; i < Matriz.length; ++i) {
            for (int j = 0; j < Matriz.length; ++j) {
                boolean LetraPermitida = false;
                for (char letra : LetrasPermitidas){
                    if (Matriz [i][j] == letra) {
                        LetraPermitida = true;
                        break;
                    }
                }
                if (!LetraPermitida){
                    return false;
                }

            }
        }
        return true;
    }

}
