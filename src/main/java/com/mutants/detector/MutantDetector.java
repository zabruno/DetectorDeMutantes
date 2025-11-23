package com.mutants.detector;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    public boolean isMutant(String[] dna) {
        char[][] matrix = buildMatrix(dna);
        int sequences = 0;

        sequences += checkHorizontal(matrix);
        if (sequences > 1) return true;

        sequences += checkVertical(matrix);
        if (sequences > 1) return true;

        sequences += checkDiagonal(matrix);
        return sequences > 1;
    }

    private char[][] buildMatrix(String[] dna) {
        int n = dna.length;
        char[][] m = new char[n][n];
        for (int i = 0; i < n; i++) {
            m[i] = dna[i].toCharArray();
        }
        return m;
    }

    private int checkHorizontal(char[][] m) { return 0; }
    private int checkVertical(char[][] m) { return 0; }
    private int checkDiagonal(char[][] m) { return 0; }
}
