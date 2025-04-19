package de.htwberlin.webtech.AnimeTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
public class AnimeController {

    @GetMapping("/anime")
    public List<String> getAnimes() {
        return Arrays.asList("Attack on Titan", "Naruto", "One Piece", "Death Note");
    }
}