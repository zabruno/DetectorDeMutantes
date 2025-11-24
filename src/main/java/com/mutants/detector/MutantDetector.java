package com.mutants.detector;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    private static final int SEQ = 4;

    public boolean isMutant(String[] dna) {
        char[][] m = buildMatrix(dna);
        int n = m.length;
        int found = 0;

        // Horizontal
        found += checkHorizontal(m, n);
        if (found > 1) return true;

        // Vertical
        found += checkVertical(m, n);
        if (found > 1) return true;

        // Diagonal (↘)
        found += checkDiagonalDown(m, n);
        if (found > 1) return true;

        // Diagonal (↗)
        found += checkDiagonalUp(m, n);
        return found > 1;
    }

    private char[][] buildMatrix(String[] dna) {
        int n = dna.length;
        char[][] m = new char[n][n];
        for (int i = 0; i < n; i++) {
            m[i] = dna[i].toCharArray();
        }
        return m;
    }

    private int checkHorizontal(char[][] m, int n) {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col <= n - SEQ; col++) {
                char c = m[row][col];
                if (c == m[row][col+1] &&
                        c == m[row][col+2] &&
                        c == m[row][col+3]) {
                    count++;
                }
            }
        }
        return count;
    }

    private int checkVertical(char[][] m, int n) {
        int count = 0;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row <= n - SEQ; row++) {
                char c = m[row][col];
                if (c == m[row+1][col] &&
                        c == m[row+2][col] &&
                        c == m[row+3][col]) {
                    count++;
                }
            }
        }
        return count;
    }

    private int checkDiagonalDown(char[][] m, int n) {
        int count = 0;
        for (int row = 0; row <= n - SEQ; row++) {
            for (int col = 0; col <= n - SEQ; col++) {
                char c = m[row][col];
                if (c == m[row+1][col+1] &&
                        c == m[row+2][col+2] &&
                        c == m[row+3][col+3]) {
                    count++;
                }
            }
        }
        return count;
    }

    private int checkDiagonalUp(char[][] m, int n) {
        int count = 0;
        for (int row = SEQ - 1; row < n; row++) {
            for (int col = 0; col <= n - SEQ; col++) {
                char c = m[row][col];
                if (c == m[row-1][col+1] &&
                        c == m[row-2][col+2] &&
                        c == m[row-3][col+3]) {
                    count++;
                }
            }
        }
        return count;
    }
}
