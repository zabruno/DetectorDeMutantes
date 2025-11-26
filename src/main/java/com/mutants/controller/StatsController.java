package com.mutants.controller;

import com.mutants.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<StatsResponse> getStats() {
        long mutants = service.countMutants();
        long humans = service.countHumans();
        return ResponseEntity.ok(new StatsResponse(mutants, humans));
    }
}
