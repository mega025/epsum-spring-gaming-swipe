package com.rinndp.gamingswipe.repositories;

import com.rinndp.gamingswipe.models.Genre;
import com.rinndp.gamingswipe.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
