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
    private String status; //watchlist, watching, fertig
    private boolean favorite;
    private String note; //notiz zu jedem anime

    public Anime(String title, String imageUrl, String status, boolean favorite, String note) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.status = status;
        this.favorite = favorite;
        this.note = note;
    }
}