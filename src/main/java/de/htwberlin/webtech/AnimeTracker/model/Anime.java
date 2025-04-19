package de.htwberlin.webtech.AnimeTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Anime {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String imageUrl;
    private String status; //watchlist, watching, fertig
    private boolean favorite;
    private String note; //notiz zu jedem anime


    public Anime(){

    }

    public Anime(String title, String imageUrl, String status, boolean favorite, String note) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.status = status;
        this.favorite = favorite;
        this.note = note;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
