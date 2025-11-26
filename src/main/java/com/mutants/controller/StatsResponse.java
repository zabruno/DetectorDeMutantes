package com.mutants.controller;

import io.swagger.v3.oas.annotations.media.Schema;

public class StatsResponse {
    @Schema(description = "Cantidad de ADN mutante detectado", example = "40")
    private long count_mutant_dna;
    @Schema(description = "Cantidad de ADN humano analizado", example = "100")
    private long count_human_dna;
    @Schema(description = "Razón entre mutantes y humanos", example = "0.4")
    private double ratio;

    public StatsResponse(long mutants, long humans) {
        this.count_mutant_dna = mutants;
        this.count_human_dna = humans;
        this.ratio = humans == 0 ? 0.0 : (double) mutants / humans;
    }

    public long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public long getCount_human_dna() {
        return count_human_dna;
    }

    public double getRatio() {
        return ratio;
    }
}
