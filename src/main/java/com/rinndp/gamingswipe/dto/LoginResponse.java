package com.rinndp.gamingswipe.dto;


import com.rinndp.gamingswipe.models.User;

public class LoginResponse extends User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String token;

    public LoginResponse(User user, String token) {
        this.id = user.getUser_id();
        this.firstName = user.getPersonalDetails().getFirstName();
        this.lastName = user.getPersonalDetails().getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
