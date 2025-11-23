package com.mutants.controller;

import com.mutants.service.MutantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final MutantService service;

    public MutantController(MutantService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> isMutant(@RequestBody DnaRequest request) {
        boolean result = service.isMutant(request.getDna());
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }
}

