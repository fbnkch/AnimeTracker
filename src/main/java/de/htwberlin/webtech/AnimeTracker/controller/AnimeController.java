package de.htwberlin.webtech.AnimeTracker.controller;

import de.htwberlin.webtech.AnimeTracker.model.Anime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/animes")
@RequestMapping("animes") //nur für Milestone 1
public class AnimeController {

    //dummy daten für Milestone1
   private final List<Anime> animeList = Arrays.asList(
        new Anime(1L, "One Piece", "https://example.com/one-piece.jpg", "watching", true, "Platz 2 der OG Big3"),
        new Anime(2L, "Naruto Shippuden", "https://example.com/naruto.jpg", "watching", true, "der beste von den OG Big3"),
        new Anime(3L, "Bleach", "https://example.com/bleach.jpg", "finished", false, "der schwächste der OG Big3")
        );
   @GetMapping
    public ResponseEntity<List<Anime>> getAnimes() {
       //später mit API call austauschen
       return ResponseEntity.ok(animeList);
   }

    // beispiel mit dummy daten
    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        if (id >= 1 && id <= animeList.size()) {
            return ResponseEntity.ok(animeList.get(id.intValue() - 1));
        }
        return ResponseEntity.notFound().build();
    }


    // beispiel mit animeList für spätere Suchfunktion
    @GetMapping("/search")
    public ResponseEntity<List<Anime>> searchAnimes(@RequestParam String query) {
        List<Anime> results = animeList.stream()
                .filter(anime -> anime.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
}