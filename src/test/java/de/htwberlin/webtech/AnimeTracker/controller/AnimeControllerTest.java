package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.Anime;
import de.htwberlin.webtech.AnimeTracker.model.User;
import de.htwberlin.webtech.AnimeTracker.repository.AnimeRepository;
import de.htwberlin.webtech.AnimeTracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "demo", password = "demo123")
    void getAnimes_returnsOk() throws Exception {
        mockMvc.perform(get("/api/animes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "demo", password = "demo123")
    void getUserAnimes_returnsUserAnime() throws Exception {

        User testUser = new User("testuser");
        User savedUser = userRepository.save(testUser);
        UUID userId = savedUser.getId();


        Anime anime = new Anime("Test", "img", 12, 1, userId);
        animeRepository.save(anime);


        mockMvc.perform(get("/api/animes/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
