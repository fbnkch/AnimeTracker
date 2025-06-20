package de.htwberlin.webtech.AnimeTracker.repository;

import de.htwberlin.webtech.AnimeTracker.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(UUID userId);
    Optional<Favorite> findByUserIdAndAnimeId(UUID userId, Long animeId);
    void deleteByUserIdAndAnimeId(UUID userId, Long animeId);
}
