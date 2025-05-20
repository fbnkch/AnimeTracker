package de.htwberlin.webtech.AnimeTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String imageUrl;
    private Integer totalEpisodes;
    private Integer watchedEpisodes;

    private String userId;

    // Konstruktor für die Verwendung im Controller
    public Anime(String title, String imageUrl, Integer totalEpisodes, Integer watchedEpisodes, String userId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.totalEpisodes = totalEpisodes;
        this.watchedEpisodes = watchedEpisodes;
        this.userId = userId;
    }

}
