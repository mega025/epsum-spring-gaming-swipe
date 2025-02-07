package com.rinndp.gamingswipe.dto;


import com.rinndp.gamingswipe.models.User;

public class LoginResponse {

    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private String password;
    private String token;

    public LoginResponse(User user, String token) {
        this.user_id = user.getUserId();
        this.firstName = user.getPersonalDetails().getFirstName();
        this.lastName = user.getPersonalDetails().getLastName();
        this.email = user.getEmail();
        this.password = user.getPersonalDetails().getPassword();
        this.token = token;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
