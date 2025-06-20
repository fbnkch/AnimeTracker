package de.htwberlin.webtech.AnimeTracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    // Konstruktor für die Verwendung im Controller
    public User(String username) {
        this.username = username;
    }
}
