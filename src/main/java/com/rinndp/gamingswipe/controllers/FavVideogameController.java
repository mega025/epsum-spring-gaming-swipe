package com.rinndp.gamingswipe.controllers;

import com.rinndp.gamingswipe.dto.ApiDelivery;
import com.rinndp.gamingswipe.dto.LoginResponse;
import com.rinndp.gamingswipe.models.FavVideogame;
import com.rinndp.gamingswipe.models.User;
import com.rinndp.gamingswipe.services.FavVideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favgames")
public class FavVideogameController {

    @Autowired
    FavVideogameService favVideogameService;


    @GetMapping("/{gameId}")
    public ResponseEntity<FavVideogame> getFavVideogame(@PathVariable Long gameId) {
        Optional<FavVideogame> optionalGame = this.favVideogameService.getGameById(gameId);
        return optionalGame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<FavVideogame>> findFavGamesByUserId(@PathVariable Long userId) {
        Optional<List<FavVideogame>> optionalList = this.favVideogameService.findFavGamesByUserId(userId);
        return optionalList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<FavVideogame> addFavVideogame(@PathVariable Long userId, @RequestBody FavVideogame favVideogame) {
        return ResponseEntity.ok(this.favVideogameService.addFavGame(userId, favVideogame));
    }

    @PostMapping("/delete/{userId}")
    public ResponseEntity<ApiDelivery> deleteFavVideogame(@PathVariable Long id, @PathVariable Long favVideogameId) {
        ApiDelivery response = this.favVideogameService.deleteFavGame(id, favVideogameId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
