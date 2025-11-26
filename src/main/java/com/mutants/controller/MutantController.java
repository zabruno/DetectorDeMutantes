package com.mutants.controller;

import com.mutants.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
@Tag(name = "Mutant Detection", description = "Operaciones para detectar ADN mutante")
public class MutantController {

    private final MutantService service;

    public MutantController(MutantService service) {
        this.service = service;
    }

    @PostMapping("/")
    @Operation(
            summary = "Detecta si el ADN pertenece a un mutante",
            description = "Analiza una secuencia de ADN y determina si corresponde a un mutante según el patrón definido"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El ADN pertenece a un mutante"),
            @ApiResponse(responseCode = "403", description = "El ADN no pertenece a un mutante"),
            @ApiResponse(responseCode = "400", description = "El formato del ADN es inválido")
    })
    public ResponseEntity<?> isMutant(@RequestBody DnaRequest request) {
        boolean result = service.isMutant(request.getDna());
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }
}

