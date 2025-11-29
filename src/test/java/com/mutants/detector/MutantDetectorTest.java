package com.mutants.detector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//CORRECCION: Tests para el algoritmo de detección
// Test Unitario puro (sin Spring Context) para máxima velocidad y aislamiento
public class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    @Test
    void shouldDetectMutantHorizontal() {
        // Caso con más de 1 secuencia horizontal
        String[] dna = {
                "AAAA", "CCCC", "TCAG", "GGTC"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantVertical() {
        // Caso con más de 1 secuencia vertical
        String[] dna = {
                "ATCG", "ATCG", "ATCG", "ATCG"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantDiagonal() {
        // Magneto Example
        String[] dna = {
                "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantInverseDiagonal() {
        // Diagonal secundaria (Abajo-Izquierda)
        String[] dna = {
                "AAAT", "AATA", "ATAA", "TAAA" // Secuencia de 'A' inversa
        };
        // Nota: Necesitamos >1 secuencia para ser mutante.
        // Agregamos una horizontal extra para cumplir la condición >1
        String[] dnaMutant = {
                "AAAG", "AAGA", "AGAA", "GGGG"
        };
        Assertions.assertTrue(detector.isMutant(dnaMutant));
    }

    @Test
    void shouldReturnFalseForHuman() {
        String[] dna = {
                "AAAT", "CCCG", "TTAC", "GGTA"
        };
        Assertions.assertFalse(detector.isMutant(dna));
    }
}