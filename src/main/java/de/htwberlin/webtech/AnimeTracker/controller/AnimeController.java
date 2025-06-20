package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.Anime;
import de.htwberlin.webtech.AnimeTracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/animes")
@CrossOrigin // Erlaubt CORS
public class AnimeController {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Anime>> getAnimes() {
        List<Anime> animes = animeRepository.findAll();
        return ResponseEntity.ok(animes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        Optional<Anime> anime = animeRepository.findById(id);
        return anime.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Anime>> searchAnimes(@RequestParam String query) {
        List<Anime> matchingAnimes = animeRepository.findByTitleContainingIgnoreCase(query);
        return ResponseEntity.ok(matchingAnimes);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Anime>> getUserAnimes(@PathVariable UUID userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<Anime> userAnimes = animeRepository.findByUserId(userId);
        return ResponseEntity.ok(userAnimes);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Anime> saveUserAnime(@PathVariable UUID userId, @RequestBody FrontendAnime frontendAnime) {
        try {
            System.out.println("Received anime from frontend: " + frontendAnime);

            // Konvertieren des Frontend-Animes zum Backend-Anime-Modell
            Anime animeToSave = new Anime(
                    frontendAnime.getTitle(),
                    frontendAnime.getImage(),  // wird zu imageUrl im Anime-Modell
                    frontendAnime.getTotal_episodes(),
                    frontendAnime.getWatched_episodes(),
                    userId
            );

            Anime savedAnime = animeRepository.save(animeToSave);

            // Für die Rückgabe ans Frontend, konvertieren wir in das erwartete Format
            FrontendAnime responseAnime = new FrontendAnime();
            responseAnime.setId(savedAnime.getId());
            responseAnime.setTitle(savedAnime.getTitle());
            responseAnime.setImage(savedAnime.getImageUrl());
            responseAnime.setTotal_episodes(savedAnime.getTotalEpisodes());
            responseAnime.setWatched_episodes(savedAnime.getWatchedEpisodes());

            return ResponseEntity.status(HttpStatus.CREATED).body(savedAnime);
        } catch (Exception e) {
            System.err.println("Error saving anime: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // NEUER ENDPUNKT: Update der watched_episodes
    @PutMapping("/user/{userId}/anime/{animeId}")
    public ResponseEntity<Anime> updateAnimeEpisodes(
            @PathVariable UUID userId,
            @PathVariable Long animeId,
            @RequestBody FrontendAnime frontendAnime) {

        try {
            System.out.println("Updating anime episodes: " + frontendAnime);

            // Suche nach Anime in der Datenbank
            Optional<Anime> existingAnime = animeRepository.findById(animeId);

            if (existingAnime.isPresent()) {
                Anime animeToUpdate = existingAnime.get();

                // Prüfen ob der Anime dem richtigen Benutzer gehört
                if (!animeToUpdate.getUserId().equals(userId)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }

                // Nur die watched_episodes aktualisieren
                animeToUpdate.setWatchedEpisodes(frontendAnime.getWatched_episodes());

                // Speichern der Änderungen
                Anime updatedAnime = animeRepository.save(animeToUpdate);

                return ResponseEntity.ok(updatedAnime);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error updating anime: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // NEUER ENDPUNKT: Löschen eines Anime
    @DeleteMapping("/user/{userId}/anime/{animeId}")
    public ResponseEntity<Void> deleteAnime(
            @PathVariable UUID userId,
            @PathVariable Long animeId) {

        try {
            System.out.println("Deleting anime with ID: " + animeId + " for user: " + userId);

            // Suche nach Anime in der Datenbank
            Optional<Anime> existingAnime = animeRepository.findById(animeId);

            if (existingAnime.isPresent()) {
                Anime animeToDelete = existingAnime.get();

                // Prüfen ob der Anime dem richtigen Benutzer gehört
                if (!animeToDelete.getUserId().equals(userId)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }

                // Löschen des Anime
                animeRepository.delete(animeToDelete);

                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error deleting anime: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DTO-Klasse für Frontend-Daten
    static class FrontendAnime {
        private Long id;
        private String title;
        private String image;  // Im Frontend heißt es 'image', nicht 'imageUrl'
        private Integer total_episodes;
        private Integer watched_episodes;

        // Getter und Setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getTotal_episodes() {
            return total_episodes;
        }

        public void setTotal_episodes(Integer total_episodes) {
            this.total_episodes = total_episodes;
        }

        public Integer getWatched_episodes() {
            return watched_episodes;
        }

        public void setWatched_episodes(Integer watched_episodes) {
            this.watched_episodes = watched_episodes;
        }

        @Override
        public String toString() {
            return "FrontendAnime{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", image='" + image + '\'' +
                    ", total_episodes=" + total_episodes +
                    ", watched_episodes=" + watched_episodes +
                    '}';
        }
    }
}
