package com.mutants.controller;

import io.swagger.v3.oas.annotations.media.Schema;

public class DnaRequest {
    @Schema(description = "Arreglo de secuencias de ADN", example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]")
    private String[] dna;

    public String[] getDna() { return dna; }
    public void setDna(String[] dna) { this.dna = dna; }
}

