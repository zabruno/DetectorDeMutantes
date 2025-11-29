package com.mutants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//CORRECCION: Añadidos  test para cubrir endpoint /mutant/
@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void shouldReturn200ForMutant() throws Exception {
        DnaRequest req = new DnaRequest();
        req.setDna(new String[]{"AAAA", "CCCC", "TCAG", "GGTC"});

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn403ForHuman() throws Exception {
        DnaRequest req = new DnaRequest();
        req.setDna(new String[]{"AAAT", "CCCG", "TTAC", "GGTA"});

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isForbidden());
    }

    // --- TESTS DE VALIDACIÓN (Los que faltaban) ---

    @Test
    void shouldReturn400ForInvalidNxN() throws Exception {
        DnaRequest req = new DnaRequest();
        req.setDna(new String[]{"AAA", "GCG"}); // 2x3 (Inválido)

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest()); // Esperamos 400 por validación manual
    }

    @Test
    void shouldReturn400ForInvalidCharacters() throws Exception {
        DnaRequest req = new DnaRequest();
        req.setDna(new String[]{"AAAA", "CCCC", "TCAG", "GGTZ"}); // Z no es válida

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400ForNullDna() throws Exception {
        // Enviamos JSON vacío
        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest()); // Esperamos 400 por @NotNull
    }
}