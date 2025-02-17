package com.rinndp.gamingswipe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genre_id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "list_genres", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<FavVideogame> videogames = new HashSet<>();


    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenreName() {
        return name;
    }

    public void setGenreName(String name) {
        this.name = name;
    }

    public Set<FavVideogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(Set<FavVideogame> favVideogames) {
        this.videogames = favVideogames;
    }
}
