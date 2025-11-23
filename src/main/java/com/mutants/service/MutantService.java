package com.mutants.service;

import com.mutants.detector.MutantDetector;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private final MutantDetector detector;

    public MutantService(MutantDetector detector) {
        this.detector = detector;
    }

    public boolean isMutant(String[] dna) {
        return detector.isMutant(dna);
    }
}

