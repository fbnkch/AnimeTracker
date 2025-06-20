package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.Anime;
import de.htwberlin.webtech.AnimeTracker.repository.AnimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AnimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    void getAnimes_returnsOk() throws Exception {
        mockMvc.perform(get("/api/animes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserAnimes_returnsUserAnime() throws Exception {
        UUID userId = UUID.randomUUID();
        Anime anime = new Anime("Test", "img", 12, 1, userId);
        animeRepository.save(anime);

        mockMvc.perform(get("/api/animes/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
