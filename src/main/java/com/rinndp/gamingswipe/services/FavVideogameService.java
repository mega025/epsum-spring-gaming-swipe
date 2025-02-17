package com.rinndp.gamingswipe.services;


import com.rinndp.gamingswipe.dto.ApiDelivery;
import com.rinndp.gamingswipe.models.FavVideogame;
import com.rinndp.gamingswipe.models.Genre;
import com.rinndp.gamingswipe.models.Platform;
import com.rinndp.gamingswipe.models.User;
import com.rinndp.gamingswipe.repositories.FavVideogameRepository;
import com.rinndp.gamingswipe.repositories.GenreRepository;
import com.rinndp.gamingswipe.repositories.PlatformRepository;
import com.rinndp.gamingswipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FavVideogameService {

    @Autowired
    private FavVideogameRepository favVideogameRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Optional<FavVideogame> getGameById(long id) {
        return this.favVideogameRepository.findById(id);
    }

    public ApiDelivery addFavGame(Long userId, FavVideogame favVideogame) {
        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId)));

        if (optionalUser.isPresent()) {
            try {
                User user = optionalUser.get();
                List<FavVideogame> favList = user.getListFavGames();
                favVideogame.setListGenres(mergeGenres(favVideogame.getListGenres()));
                favVideogame.setListPlatforms(mergePlatforms(favVideogame.getListPlatforms()));

                boolean alreadyAdded = false;
                for (FavVideogame fav : favList) {
                    if(fav.getName().equals(favVideogame.getName())) {
                        alreadyAdded = true;
                    }
                }

                if (alreadyAdded) {
                    return new ApiDelivery("Game already added", false, 400, null, null);
                } else {
                    user.setListFavGames(favList);
                    favList.add(favVideogame);
                    this.favVideogameRepository.save(favVideogame);
                    return new ApiDelivery("Game added correctly", true, 200, null, null);
                }
                } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("User not found " + userId);
        }
        return null;
    }

    private List<Genre> mergeGenres(List<Genre> newGenres) {
        List<Genre> existingGenres = genreRepository.findAll();
        return newGenres.stream()
                .map(newGenre -> existingGenres.stream()
                        .filter(existing -> existing.getGenreName().equals(newGenre.getGenreName()))
                        .findFirst()
                        .orElse(newGenre))
                .collect(Collectors.toList());
    }

    private List<Platform> mergePlatforms(List<Platform> newPlatforms) {
        List<Platform> existingPlatforms = platformRepository.findAll();
        return newPlatforms.stream()
                .map(newPlatform -> existingPlatforms.stream()
                        .filter(existing -> existing.getAbbreviation().equals(newPlatform.getAbbreviation()))
                        .findFirst()
                        .orElse(newPlatform))
                .collect(Collectors.toList());
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

    public ApiDelivery deleteFavGame(Integer position, Long userId) {
        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found " + userId)));

        if (optionalUser.isPresent()) {
            try {
                User user = optionalUser.get();
                List<FavVideogame> favList = user.getListFavGames();
                favList.remove(favList.get(position));
                user.setListFavGames(favList);
                this.userRepository.save(user);
                return new ApiDelivery("Game deleted correctly", true, 200, null, null);
            } catch (Exception e) {
                return new ApiDelivery("Error", false, 400, null, null);
            }
        }

        return new ApiDelivery("User not found", false, 400, null, null);

    }
}
