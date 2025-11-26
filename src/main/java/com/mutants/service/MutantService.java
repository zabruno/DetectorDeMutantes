package com.mutants.service;

import com.mutants.detector.MutantDetector;
import com.mutants.model.DnaRecord;
import com.mutants.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class MutantService {

    private final MutantDetector detector;
    private final DnaRecordRepository repository;

    public MutantService(MutantDetector detector, DnaRecordRepository repository) {
        this.detector = detector;
        this.repository = repository;
    }

    public boolean isMutant(String[] dna) {

        String hash = hashDna(dna);

        // If already exists, return stored result
        return repository.findByDnaHash(hash)
                .map(DnaRecord::isMutant)
                .orElseGet(() -> {
                    boolean result = detector.isMutant(dna);
                    repository.save(new DnaRecord(hash, result));
                    return result;
                });
    }

    private String hashDna(String[] dna) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(Arrays.toString(dna).getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash error", e);
        }
    }
}

