package com.rinndp.gamingswipe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable (
            name = "videogame_platforms",
            joinColumns = @JoinColumn(name="videogame_id"),
            inverseJoinColumns = @JoinColumn(name="platform_id")
    )
    private List<Platform> list_platforms = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable (
            name = "videogame_genres",
            joinColumns = @JoinColumn(name="videogame_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private List<Genre> list_genres = new ArrayList<>();

    @ManyToMany(mappedBy = "list_fav_games")
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public Long getVideogameId() {
        return videogame_id;
    }

    public void setVideogameId(Long videogame_id) {
        this.videogame_id = videogame_id;
    }

    public String getName() {
        return name;
    }

    public Double getRatingScore() {
        return rating_score;
    }

    public void setRatingScore(Double rating_score) {
        this.rating_score = rating_score;
    }

    public Integer getReleaseYear() {
        return release_year;
    }

    public void setReleaseYear(Integer release_year) {
        this.release_year = release_year;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Platform> getListPlatforms() {
        return list_platforms;
    }

    public void setListPlatforms(List<Platform> list_platforms) {
        this.list_platforms = list_platforms;
    }

    public List<Genre> getListGenres() {
        return list_genres;
    }

    public void setListGenres(List<Genre> list_genres) {
        this.list_genres = list_genres;
    }
}
