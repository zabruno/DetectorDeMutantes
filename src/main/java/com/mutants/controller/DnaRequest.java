package com.mutants.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DnaRequest {
    @Schema(description = "Arreglo de secuencias de ADN", example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]")
    //CORRECCION: Valida nulo / Valida vacío
    @NotNull(message = "DNA no puede ser nulo")
    @NotEmpty(message = "DNA no puede estar vacío")
    private String[] dna;

}

