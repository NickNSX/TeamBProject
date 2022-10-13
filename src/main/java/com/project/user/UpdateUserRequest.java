package com.project.user;

import com.project.common.Request;

public class UpdateUserRequest implements Request<User> {

    private String userId;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private boolean isActive;
    private String role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public User extractEntity() {
        User extractedEntity = new User();
        extractedEntity.setUsername(this.username);
        extractedEntity.setEmail(this.email);
        extractedEntity.setGivenName(this.givenName);
        extractedEntity.setSurname(this.surname);
        return extractedEntity;
    }

        // @Override
        // public String toString() {
        //     return "Updated {" +
        //             "email = '" + email + "' " +
        //             "given_name = '" + isActive + "' " +
        //             "surname = '" + surname + "' " +
        //             "password = '" + password + "' " +
        //             "is_active = '" + isActive + "' " +
        //             "role = '" + role + "' " +
        //             "}";
        // }
    
}
