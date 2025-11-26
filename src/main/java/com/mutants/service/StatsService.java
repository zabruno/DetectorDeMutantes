package com.mutants.service;

import com.mutants.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsService(DnaRecordRepository repository) {
        this.repository = repository;
    }

    public long countMutants() {
        return repository.countByMutant(true);
    }

    public long countHumans() {
        return repository.countByMutant(false);
    }
}