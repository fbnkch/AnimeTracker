package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.User;
import de.htwberlin.webtech.AnimeTracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registerUser_success() throws Exception {
        String username = "testuser_" + System.currentTimeMillis();
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void registerUser_duplicate() throws Exception {
        String username = "duplicateuser_" + System.currentTimeMillis();
        userRepository.save(new User(username));
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Benutzername existiert bereits"));
    }

    @Test
    void loginUser_success() throws Exception {
        String username = "loginuser_" + System.currentTimeMillis();
        userRepository.save(new User(username));
        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void loginUser_notFound() throws Exception {
        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"notfounduser\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Benutzer nicht gefunden"));
    }
}
