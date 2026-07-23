package com.example.traveling.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DestinationControllerIntegrationTest {

    @Autowired
    private DestinationController destinationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(destinationController).build();
    }

    @Test
    @DisplayName("GET /api/destinations - sikeresen visszaadja az úticélok listáját")
    void findAllDestinations_ShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/destinations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/destinations - sikeresen létrehoz egy új úticélt")
    void createDestination_ShouldReturn201Created() throws Exception {
        String jsonPayload = """
                {
                    "cityName": "Budapest",
                    "region": "Pest",
                    "description": "nem tudom mit írjak",
                    "popularity": 5
                }
                """;

        mockMvc.perform(post("/api/destinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cityName").value("Budapest"))
                .andExpect(jsonPath("$.region").value("Pest"))
                .andExpect(jsonPath("$.popularity").value(5));
    }
}