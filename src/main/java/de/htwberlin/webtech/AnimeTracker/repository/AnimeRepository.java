package de.htwberlin.webtech.AnimeTracker.repository;

import de.htwberlin.webtech.AnimeTracker.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByTitleContainingIgnoreCase(String title);
    List<Anime> findByUserId(UUID userId);
}
