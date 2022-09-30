package com.project.user;

import java.util.UUID;

import com.project.common.Request;

public class NewUserRequest implements Request<User> {

    private String givenName;
    private String surname;
    private String email;
    private String username;
    private String password;
    private boolean isActive;
    private String role;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public User extractEntity() {
        User extractedEntity = new User();
        extractedEntity.setId(UUID.randomUUID());
        extractedEntity.setGivenName(this.givenName);
        extractedEntity.setSurname(this.surname);
        extractedEntity.setEmail(this.email);
        extractedEntity.setUsername(this.username);
        extractedEntity.setPassword(this.password);
        extractedEntity.setIsActive(this.isActive);

        //TODO Change this to ers user role uuid value
        // extractedEntity.setRole(new Role(UUID.fromString("5a2e0415-ee08-440f-ab8a-778b37ff6874"), "JUNIOR"));
        return extractedEntity;
    }

}