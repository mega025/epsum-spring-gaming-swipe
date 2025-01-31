package com.rinndp.gamingswipe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genre_id;

    @Column(nullable = false, unique = true)
    private String genre_name;

    @ManyToMany(mappedBy = "list_genres")
    @JsonBackReference
    private Set<FavVideogame> videogames = new HashSet<>();


    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public Set<FavVideogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(Set<FavVideogame> favVideogames) {
        this.videogames = favVideogames;
    }
}
