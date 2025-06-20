package de.htwberlin.webtech.AnimeTracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;

    private UUID userId;
    private Long animeId;
}
