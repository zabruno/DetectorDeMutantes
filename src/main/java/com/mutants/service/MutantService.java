package com.mutants.service;

import com.mutants.detector.MutantDetector;
import com.mutants.model.DnaRecord;
import com.mutants.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

@Service
public class MutantService {

    private final MutantDetector detector;
    private final DnaRecordRepository repository;

    public MutantService(MutantDetector detector, DnaRecordRepository repository) {
        this.detector = detector;
        this.repository = repository;
    }

    private void validateDna(List<String> dna) {
        if (dna == null || dna.isEmpty()) {
            throw new IllegalArgumentException("El ADN no puede ser nulo o vacío");
        }

        int n = dna.size();

        for (String row : dna) {
            if (row == null || row.isEmpty()) {
                throw new IllegalArgumentException("Cada fila del ADN debe tener contenido");
            }

            if (row.length() != n) {
                throw new IllegalArgumentException("La matriz debe ser NxN");
            }

            if (!row.matches("^[ATCG]+$")) {
                throw new IllegalArgumentException("El ADN solo puede contener las letras A,T,C,G");
            }
        }
    }

    public boolean isMutant(String[] dnaRaw) {
        if (dnaRaw == null || dnaRaw.length == 0) {
            throw new IllegalArgumentException("El ADN no puede ser nulo o vacío");
        }

        //Normalizar entradas
        String[] dna = Arrays.stream(dnaRaw).map(String::toUpperCase).toArray(String[]::new);

        validateDna(List.of(dna));
        String hash = hashDna(dna);

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
        } catch (Exception e) {
            throw new RuntimeException("Error generando hash de ADN", e);
        }
    }
}
