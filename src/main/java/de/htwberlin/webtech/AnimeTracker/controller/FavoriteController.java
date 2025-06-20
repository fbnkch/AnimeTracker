package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.Favorite;
import de.htwberlin.webtech.AnimeTracker.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @PostMapping("/user/{userId}/anime/{animeId}")
    public ResponseEntity<Favorite> addFavorite(@PathVariable UUID userId, @PathVariable Long animeId) {
        if (favoriteRepository.findByUserIdAndAnimeId(userId, animeId).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Favorite favorite = new Favorite(null, userId, animeId);
        return ResponseEntity.ok(favoriteRepository.save(favorite));
    }

    @DeleteMapping("/user/{userId}/anime/{animeId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable UUID userId, @PathVariable Long animeId) {
        favoriteRepository.deleteByUserIdAndAnimeId(userId, animeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favorite>> getFavorites(@PathVariable UUID userId) {
        return ResponseEntity.ok(favoriteRepository.findByUserId(userId));
    }
}
