package com.mutants.service;

import com.mutants.detector.MutantDetector;
import com.mutants.model.DnaRecord;
import com.mutants.repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//CORRECCION:Añadidos test unitarios para MutantService. Utilizamos Mockito para simular BD.
@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

    @Mock
    private DnaRecordRepository repository;

    @Mock
    private MutantDetector detector;

    @InjectMocks
    private MutantService service;

    @Test
    void shouldReturnTrueIfMutantExistsInDb() {
        // Simulamos que YA existe en BD y es mutante
        when(repository.findByDnaHash(anyString())).thenReturn(Optional.of(new DnaRecord("hash", true)));

        String[] dna = {"AAAA", "CCCC", "GTCA", "ACTG"};
        boolean result = service.isMutant(dna);

        assertTrue(result);
        // Verificar que NO se llamó al detector (ahorro de recursos)
        verify(detector, never()).isMutant(any());
    }

    @Test
    void shouldCallDetectorIfDnaNotInDb() {
        // Simulamos que NO está en BD
        when(repository.findByDnaHash(anyString())).thenReturn(Optional.empty());
        when(detector.isMutant(any())).thenReturn(true);

        String[] dna = {"AAAA", "CCCC", "GTCA", "ACTG"};
        boolean result = service.isMutant(dna);

        assertTrue(result);
        // Verificar que se guardó en BD
        verify(repository, times(1)).save(any(DnaRecord.class));
    }
}