package com.mutants.controller;

import com.mutants.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@Tag(name = "Statistics", description = "Estadísticas de detección de ADN")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(
            summary = "Obtiene estadísticas de ADN analizado",
            description = "Devuelve la cantidad de humanos, de mutantes y la razón entre ambos"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente")
    })
    public ResponseEntity<StatsResponse> getStats() {
        long mutants = service.countMutants();
        long humans = service.countHumans();
        return ResponseEntity.ok(new StatsResponse(mutants, humans));
    }
}
