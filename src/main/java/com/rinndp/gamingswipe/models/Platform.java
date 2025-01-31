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
    private String platform_name;

    @ManyToMany(mappedBy = "list_platforms", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<FavVideogame> favVideogames = new HashSet<>();

    public Set<FavVideogame> getVideogames() {
        return favVideogames;
    }

    public void setVideogames(Set<FavVideogame> favVideogames) {
        this.favVideogames = favVideogames;
    }

    public Long getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(Long platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }
}
