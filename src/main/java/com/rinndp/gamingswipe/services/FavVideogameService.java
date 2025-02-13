package com.rinndp.gamingswipe.services;


import com.rinndp.gamingswipe.dto.ApiDelivery;
import com.rinndp.gamingswipe.models.FavVideogame;
import com.rinndp.gamingswipe.models.User;
import com.rinndp.gamingswipe.repositories.FavVideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class FavVideogameService {

    @Autowired
    private FavVideogameRepository favVideogameRepository;

    @Autowired
    private UserService userService;

    public Optional<FavVideogame> getGameById(long id) {
        return this.favVideogameRepository.findById(id);
    }

    public FavVideogame addFavGame(Long userId, FavVideogame favVideogame) {
        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId)));

        if (optionalUser.isPresent()) {
            try {
                User user = optionalUser.get();
                List<FavVideogame> favList = user.getListFavGames();
                favList.add(favVideogame);
                user.setListFavGames(favList);
                return this.favVideogameRepository.save(favVideogame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("User not found " + userId);
        }
        return null;
    }

    public Optional<List<FavVideogame>> findFavGamesByUserId(Long userId) {
        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId)));

        if (optionalUser.isPresent()) {
            try {
                User user = optionalUser.get();
                List<FavVideogame> favList = user.getListFavGames();
                return Optional.ofNullable(favList);
            } catch (Exception e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public ApiDelivery deleteFavGame(Long favGameId, Long userId) {
        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId)));

        Optional<FavVideogame> optionalGame = Optional.ofNullable(getGameById(favGameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found " + favGameId)));

        if (optionalUser.isPresent() && optionalGame.isPresent()) {
            try {
                User user = optionalUser.get();
                FavVideogame favVideogame = optionalGame.get();
                List<FavVideogame> favList = user.getListFavGames();
                favList.remove(favVideogame);
                return new ApiDelivery("Game deleted corectly", true, 200, null, null);
            } catch (Exception e) {
                return new ApiDelivery("Error", false, 400, null, null);
            }
        }
        return new ApiDelivery("User or game not found", false, 400, null, null);
    }
}
