package com.mutants.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//CORRECCION: Verifica que tenga los campos correctos
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnStatsStructure() throws Exception {
        mockMvc.perform(get("/stats/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna").isNumber())
                .andExpect(jsonPath("$.count_human_dna").isNumber())
                .andExpect(jsonPath("$.ratio").isNumber());
    }
}