package com.rinndp.gamingswipe.repositories;

import com.rinndp.gamingswipe.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
