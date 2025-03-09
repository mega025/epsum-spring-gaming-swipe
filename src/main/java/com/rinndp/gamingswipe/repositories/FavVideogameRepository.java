package com.rinndp.gamingswipe.repositories;

import com.rinndp.gamingswipe.models.FavVideogame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavVideogameRepository extends JpaRepository<FavVideogame, Long> {
    public Optional<FavVideogame> findByName(String name);
}
