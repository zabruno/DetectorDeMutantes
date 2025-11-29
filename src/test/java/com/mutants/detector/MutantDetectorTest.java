package com.mutants.detector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    // --- CASOS MUTANTES (Horizontal) ---

    @Test
    void shouldDetectMutantHorizontalFirstRow() {
        // Mutante en fila 0 y fila 2
        String[] dna = {
                "AAAA", "CCCC", "GTCA", "ACTG"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantHorizontalLastRow() {
        // Mutante en fila 0 y fila 5
        String[] dna = {
                "AAAA", "GTCA", "GTCA", "CCCC"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    // --- CASOS MUTANTES (Vertical) ---

    @Test
    void shouldDetectMutantVerticalFirstCol() {
        // Columna 0 y Columna 1
        String[] dna = {
                "ACGT",
                "ACGT",
                "ACGT",
                "ACGT"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantVerticalLastCol() {
        // Columna 3 y Columna 0
        String[] dna = {
                "AGGG",
                "AGGG",
                "AGGG",
                "AGGG"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    // --- CASOS MUTANTES (Diagonales) ---

    @Test
    void shouldDetectMutantDiagonalMain() {
        // Diagonal Principal (↘) + Horizontal
        String[] dna = {
                "AGAAT",
                "TACGT",
                "TTATG",
                "GGTGA",
                "CCCCC" // Horizontal extra para cumplir >1
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantDiagonalSecondary() {
        // Diagonal Secundaria (↙) + Horizontal
        String[] dna = {
                "AAAG",
                "AAGA",
                "AGAA",
                "GGGG" // Horizontal para sumar la 2da secuencia
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldDetectMutantCross() {
        // Cruce de filas y columnas
        String[] dna = {
                "AATCG",
                "AATCG",
                "AATCG",
                "AATCG", // Vertical en Col 0
                "AAAAA"  // Horizontal en Fila 4
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    // --- CASOS HUMANOS (Negativos) ---

    @Test
    void shouldNotDetectHumanRandom() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        Assertions.assertFalse(detector.isMutant(dna));
    }

    @Test
    void shouldNotDetectHumanWithOnlyOneSequence() {
        // Requisito explícito: Humano con 1 sola secuencia NO es mutante
        String[] dna = {
                "AAAA", // 1 secuencia
                "CAGT",
                "TTAT",
                "AGAC"
        };
        Assertions.assertFalse(detector.isMutant(dna));
    }

    @Test
    void shouldNotDetectAlmostMutant() {
        // Secuencias de 3 letras (AAA), no 4
        String[] dna = {
                "AAAT",
                "AAAT",
                "AAAT",
                "AAAT"
        };
        // Verticalmente son AAAA, asi que cambiamos para que no forme vertical
        String[] dnaHuman = {
                "AAAT",
                "GCTA",
                "AAAT",
                "CGTA"
        };
        Assertions.assertFalse(detector.isMutant(dnaHuman));
    }

    // --- TAMAÑOS Y BORDES ---

    @Test
    void shouldHandleMinSizeMutant() {
        // Matriz 4x4 (Mínimo posible)
        String[] dna = {
                "AAAA",
                "CCCC",
                "CAGT",
                "ACTG"
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }

    @Test
    void shouldHandleMinSizeHuman() {
        String[] dna = {
                "AAAT",
                "CCCG",
                "GGGA",
                "TTTC"
        };
        Assertions.assertFalse(detector.isMutant(dna));
    }

    @Test
    void shouldHandleLargeMatrix() {
        // Ejemplo simple 10x10
        String[] dna = {
                "ATGCATGCAT",
                "GCATGCATGC",
                "ATGCATGCAT",
                "GCATGCATGC",
                "ATGCATGCAT",
                "GCATGCATGC",
                "AAAAATGCAT", // Horizontal
                "GCATGCATGC",
                "ATGCATGCAT",
                "AAAAATGCAT"  // Horizontal
        };
        Assertions.assertTrue(detector.isMutant(dna));
    }
}