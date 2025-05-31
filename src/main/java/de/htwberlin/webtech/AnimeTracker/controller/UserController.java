package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.User;
import de.htwberlin.webtech.AnimeTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        // Prüfen, ob der Benutzername bereits existiert
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Benutzername existiert bereits");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // Benutzer erstellen und speichern
        User user = new User(userRequest.getUsername());
        User savedUser = userRepository.save(user);

        // Erfolgreiche Antwort mit Benutzer-ID senden
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedUser.getId());
        response.put("username", savedUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest userRequest) {
        // Benutzer anhand des Benutzernamens suchen
        Optional<User> userOptional = userRepository.findByUsername(userRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Erfolgreiche Antwort mit Benutzer-ID senden
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            return ResponseEntity.ok(response);
        } else {
            // Benutzer nicht gefunden
            Map<String, String> response = new HashMap<>();
            response.put("error", "Benutzer nicht gefunden");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // DTO-Klasse für Benutzeranfragen
    static class UserRequest {
        private String username;

        public UserRequest() {

        }
        public UserRequest(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString(){
            return "UserRequest{username='" + username + "'}";
        }
    }
}
