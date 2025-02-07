package com.rinndp.gamingswipe.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class FavVideogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videogame_id;

    @Column(nullable = false, unique = true)
    private String name;

    private Double rating_score;

    private Integer release_year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "videogame_platforms",
            joinColumns = @JoinColumn(name="videogame_id"),
            inverseJoinColumns = @JoinColumn(name="platform_id")
    )
    private Set<Platform> list_platforms = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "videogame_genres",
            joinColumns = @JoinColumn(name="videogame_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<Genre> list_genres = new HashSet<>();

    public Long getVideogame_id() {
        return videogame_id;
    }

    public void setVideogame_id(Long videogame_id) {
        this.videogame_id = videogame_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Platform> getList_platforms() {
        return list_platforms;
    }

    public void setList_platforms(Set<Platform> list_platforms) {
        this.list_platforms = list_platforms;
    }

    public Set<Genre> getList_genres() {
        return list_genres;
    }

    public void setList_genres(Set<Genre> list_genres) {
        this.list_genres = list_genres;
    }
}
