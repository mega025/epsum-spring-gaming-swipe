package com.rinndp.gamingswipe.repositories;

import com.rinndp.gamingswipe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
