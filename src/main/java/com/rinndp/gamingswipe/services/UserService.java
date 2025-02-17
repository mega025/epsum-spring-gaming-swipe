package com.rinndp.gamingswipe.services;

import com.rinndp.gamingswipe.dto.ApiDelivery;
import com.rinndp.gamingswipe.dto.LoginResponse;
import com.rinndp.gamingswipe.models.PersonalDetails;
import com.rinndp.gamingswipe.models.User;
import com.rinndp.gamingswipe.repositories.UserRepository;
import com.rinndp.gamingswipe.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserById(long id) {
        User optionalUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        this.userRepository.delete(optionalUser);
    }

    public ApiDelivery updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {

            existingUser.getPersonalDetails().setFirstName(updatedUser.getPersonalDetails().getFirstName());
            existingUser.getPersonalDetails().setLastName(updatedUser.getPersonalDetails().getLastName());
            existingUser.getPersonalDetails().setImage_url(updatedUser.getPersonalDetails().getImage_url());
            existingUser.getPersonalDetails().setPassword(updatedUser.getPersonalDetails().getPassword());

             this.userRepository.save(existingUser);
             return new ApiDelivery("User updated correctly", true, 200, null, null);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }



    public User createUser(User user) {
        Optional<User> optionalUser = this.userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            throw new RuntimeException("This email already exists");
        } else {
            User newUser = new User();
            newUser.setEmail(user.getEmail());

            PersonalDetails newPersonalDetails = new PersonalDetails();

            newPersonalDetails.setLastName(user.getPersonalDetails().getLastName());
            newPersonalDetails.setFirstName(user.getPersonalDetails().getFirstName());
            newPersonalDetails.setPassword(this.passwordEncoder.encode(user.getPersonalDetails().getPassword()));

            newUser.setPersonalDetails(newPersonalDetails);
            newUser.setListFavGames(new ArrayList<>());

            return this.userRepository.save(newUser);
        }
    }

    public ApiDelivery<LoginResponse> login (String email, String password) {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);

        if(optionalUser.isEmpty())
            return new ApiDelivery<>("User not found", false, 404, null, "not found");

        User user = optionalUser.get();
        if(!this.passwordEncoder.matches(password, user.getPersonalDetails().getPassword()))
            return new ApiDelivery<>("Incorrect password", false, 400, null, "Incorrect password");

        String token = createToken(email);
        LoginResponse loggedUser = new LoginResponse(user, token);
        return new ApiDelivery<>("Login successful", true, 200, loggedUser , "Login successful");
    }

    public String createToken (String email) {
        return jwtUtil.generateToken(email);
    }
}
