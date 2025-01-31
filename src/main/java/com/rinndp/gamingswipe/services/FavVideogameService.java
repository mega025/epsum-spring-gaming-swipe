package com.rinndp.gamingswipe.services;


import com.rinndp.gamingswipe.models.FavVideogame;
import com.rinndp.gamingswipe.models.User;
import com.rinndp.gamingswipe.repositories.FavVideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FavVideogameService {

    @Autowired
    private FavVideogameRepository favVideogameRepository;

    @Autowired
    private UserService userService;

    public Set<FavVideogame> findAllFavVideogamesByUserId(Long id) {

        Optional<User> optionalUser = this.userService.getUserById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        Set<FavVideogame> listFavGames = user.getListFavGames();
        return listFavGames;

    }
}
