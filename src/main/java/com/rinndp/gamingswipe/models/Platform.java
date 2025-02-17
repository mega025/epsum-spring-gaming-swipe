package com.rinndp.gamingswipe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platform_id;

    @Column(nullable = false, unique = true)
    private String abbreviation;

    @ManyToMany(mappedBy = "list_platforms", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<FavVideogame> videogames = new HashSet<>();


    public Set<FavVideogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(Set<FavVideogame> videogames) {
        this.videogames = videogames;
    }

    public Long getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(Long platform_id) {
        this.platform_id = platform_id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String platform_name) {
        this.abbreviation = platform_name;
    }
}
