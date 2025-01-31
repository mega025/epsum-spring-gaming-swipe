package com.rinndp.gamingswipe.services;


import com.rinndp.gamingswipe.repositories.FavVideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FavVideogameService {

    @Autowired
    private FavVideogameRepository favVideogameRepository;

    @Autowired
    private UserService userService;


}
