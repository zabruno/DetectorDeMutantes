package com.mutants.service;

import com.mutants.detector.MutantDetector;
import com.mutants.repository.DnaRecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.MessageDigest;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private DnaRecordRepository repository;

    @Mock
    private MutantDetector detector;

    @Mock
    private MessageDigest messageDigest;

    @InjectMocks
    private MutantService service;

    // --- TESTS DE VALIDACIÓN (Los que faltaban) ---

    @Test
    void shouldThrowExceptionForNullDna() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isMutant(null);
        });
    }

    @Test
    void shouldThrowExceptionForEmptyDna() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isMutant(new String[]{});
        });
    }

    @Test
    void shouldThrowExceptionForNonNxNMatrix() {
        // Matriz irregular (3 filas, longitud 4, 4, 3)
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCA" // Falta una letra
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isMutant(dna);
        });
    }

    @Test
    void shouldThrowExceptionForInvalidCharacters() {
        // Letra 'Z' no permitida
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTZ"
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isMutant(dna);
        });
    }

    @Test
    void shouldThrowExceptionForNumbers() {
        // Números no permitidos
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "1234"
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isMutant(dna);
        });
    }
}