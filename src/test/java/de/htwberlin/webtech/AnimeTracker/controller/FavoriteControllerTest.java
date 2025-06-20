package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.repository.FavoriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    @WithMockUser(username = "demo", password = "demo123")
    void addAndRemoveFavorite() throws Exception {
        UUID userId = UUID.randomUUID();
        Long animeId = 123L;

        // Hinzufügen
        mockMvc.perform(post("/api/favorites/user/" + userId + "/anime/" + animeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.animeId").value(animeId));

        // Abrufen
        mockMvc.perform(get("/api/favorites/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Entfernen
        mockMvc.perform(delete("/api/favorites/user/" + userId + "/anime/" + animeId))
                .andExpect(status().isNoContent());
    }
}
