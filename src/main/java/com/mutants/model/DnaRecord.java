package com.mutants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dna_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dnaHash")
})
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String dnaHash;

    @Column(nullable = false)
    private boolean mutant;

    public DnaRecord() {
    }

    public DnaRecord(String dnaHash, boolean mutant) {
        this.dnaHash = dnaHash;
        this.mutant = mutant;
    }

    public Long getId() {
        return id;
    }

    public String getDnaHash() {
        return dnaHash;
    }

    public boolean isMutant() {
        return mutant;
    }
}
