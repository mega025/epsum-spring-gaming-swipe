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

    private String image_url;

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

    public Double getRating_score() {
        return rating_score;
    }

    public void setRating_score(Double rating_score) {
        this.rating_score = rating_score;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
