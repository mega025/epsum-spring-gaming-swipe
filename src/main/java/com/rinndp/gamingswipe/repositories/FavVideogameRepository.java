package com.rinndp.gamingswipe.repositories;

import com.rinndp.gamingswipe.models.FavVideogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavVideogameRepository extends JpaRepository<FavVideogame, Long> {
}
