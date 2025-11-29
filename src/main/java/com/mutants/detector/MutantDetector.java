package com.mutants.detector;

import org.springframework.stereotype.Component;


//CORRECCION: Algoritmo optimizado, no se itera varias veces
@Component
public class MutantDetector {

    private static final int SEQ_LEN = 4;

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        // Optimization: Trabajamos con char[][] para acceso más rápido por índice
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        int mutantSequences = 0;

        // ÚNICA PASADA: Recorremos la matriz una sola vez
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Optimización: Si ya no queda espacio en ninguna dirección, saltamos
                char current = matrix[i][j];

                // 1. HORIZONTAL (Hacia la derecha) -> (i, j+1)...
                // Solo chequeamos si cabe la secuencia (j + 3 < n)
                if (j + SEQ_LEN <= n) {
                    if (current == matrix[i][j+1] &&
                            current == matrix[i][j+2] &&
                            current == matrix[i][j+3]) {
                        mutantSequences++;
                        if (mutantSequences > 1) return true; // Terminación temprana
                    }
                }

                // 2. VERTICAL (Hacia abajo) -> (i+1, j)...
                // Solo chequeamos si cabe hacia abajo (i + 3 < n)
                if (i + SEQ_LEN <= n) {
                    if (current == matrix[i+1][j] &&
                            current == matrix[i+2][j] &&
                            current == matrix[i+3][j]) {
                        mutantSequences++;
                        if (mutantSequences > 1) return true;
                    }
                }

                // 3. DIAGONAL PRINCIPAL (Hacia abajo-derecha) ↘
                if (i + SEQ_LEN <= n && j + SEQ_LEN <= n) {
                    if (current == matrix[i+1][j+1] &&
                            current == matrix[i+2][j+2] &&
                            current == matrix[i+3][j+3]) {
                        mutantSequences++;
                        if (mutantSequences > 1) return true;
                    }
                }

                // 4. DIAGONAL SECUNDARIA (Hacia abajo-izquierda) ↙
                // Nota: Reemplaza a tu "checkDiagonalUp". Miramos hacia "atrás" en columnas pero "abajo" en filas.
                if (i + SEQ_LEN <= n && j - SEQ_LEN >= -1) {
                    if (current == matrix[i+1][j-1] &&
                            current == matrix[i+2][j-2] &&
                            current == matrix[i+3][j-3]) {
                        mutantSequences++;
                        if (mutantSequences > 1) return true;
                    }
                }
            }
        }

        return false; // Si terminamos el ciclo y no hay más de 1 secuencia
    }
}