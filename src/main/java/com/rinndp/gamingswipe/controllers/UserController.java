package com.rinndp.gamingswipe.controllers;

import com.rinndp.gamingswipe.dto.ApiDelivery;
import com.rinndp.gamingswipe.dto.LoginRequest;
import com.rinndp.gamingswipe.dto.LoginResponse;
import com.rinndp.gamingswipe.models.User;
import com.rinndp.gamingswipe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<ApiDelivery> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        ApiDelivery response = this.userService.updateUser(userId, updatedUser);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiDelivery<LoginResponse>> login(@RequestBody LoginRequest credentials) {
        ApiDelivery<LoginResponse> response = this.userService.login(credentials.getEmail(), credentials.getPassword());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
