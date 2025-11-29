package com.mutants.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "dna_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dnaHash")
})

//CORRECCION: Se aplica lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String dnaHash;

    @Column(nullable = false)
    private boolean mutant;

    //Se agrega createdAt
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public DnaRecord(String dnaHash, boolean mutant) {
        this.dnaHash = dnaHash;
        this.mutant = mutant;
        this.createdAt = LocalDateTime.now();
    }
}
